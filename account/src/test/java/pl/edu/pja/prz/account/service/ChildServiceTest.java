package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.ChildBuilder;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChildServiceTest {
	private Address address;
	private Phone phone;
	private FullName fullName;

	@Mock
	private ChildRepository childRepository;
	@Mock
	private PeselService peselService;

	private ChildService childService;

	@BeforeEach
	void setUp() {
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		childService = new ChildService(childRepository, peselService);
	}

	@Test
	void should_createChild() {
		//given
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withGuardians(new HashSet<>())
				.build();

		//when
		when(peselService.extractDateOfBirth(any())).thenReturn(LocalDate.now());
		when(peselService.extractGender(any())).thenReturn(Gender.MALE);
		when(childRepository.save(any(Child.class))).thenReturn(child);
		var createdChild = childService.createChild(address, fullName, "00440758725", new StudyPeriod());

		//then
		assertNotNull(createdChild);
	}

	@Test
	void Should_getChildById() {
		//given
		var id = UUID.randomUUID();

		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();


		//when
		when(childRepository.findById(id)).thenReturn(Optional.ofNullable(child));
		var result = childService.getById(id);

		//then
		assertNotNull(result);
		verify(childRepository, times(1)).findById(id);
	}

	@Test
	void Should_updateChild() {
		//given
		var id = UUID.randomUUID();

		var childOld = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();
		childOld.setId(id);

		var childSpy = Mockito.spy(childOld);

		var newChild = ChildBuilder.aChild()
				.withPeselNumber("777777")
				.withFullName(fullName)
				.withAddress(address)
				.build();
		newChild.setId(id);

		//when
		when(childRepository.findById(id)).thenReturn(Optional.of(childSpy));
		when(childRepository.save(any())).thenReturn(childOld);

		childService.update(newChild);

		//then
		assertEquals(newChild, childSpy);
	}

	@Test
	void Should_ThrowMoreThanOneElement_When_FoundMorChildren() {
		//given
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();

		var  childSecond = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();


		when(childRepository.findReadOnly(any(),any())).thenReturn(List.of(child, childSecond));

		//when
		assertThrows( MoreThanOneElement.class, ()-> {
			this.childService.findByFullNameOrAddressReadOnly(fullName, null);
		});
		assertThrows(MoreThanOneElement.class, ()-> {
			this.childService.findByFullNameOrAddressReadOnly(fullName, "test");
		});

	}

	@Test
	void Should_GetAllChild(){
		//given
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();

		var childSecond = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.build();

		//when
		when(childRepository.findAll()).thenReturn(List.of(child,childSecond));
		var allChildren = this.childService.getAll();

		//then
		verify(childRepository, times(1)).findAll();
		assertEquals(2, allChildren.size());

	}

	@Test
	void Should_CreateChildrenWithPesel() {
		var studyPeriod = new StudyPeriod(LocalDate.of(2014, Month.SEPTEMBER, 01),
				LocalDate.of(2015, Month.JULY, 26));

		var child = ChildBuilder.aChild()
				.withAge(new Age(LocalDate.of(1994,7,11)))
				.withPeselNumber("94071105694")
				.withFullName(fullName)
				.withAddress(address)
				.withStudyPeriod(studyPeriod)
				.withGender(Gender.MALE)
				.withChildStatuses(Set.of(ChildStatus.NEW))
				.build();

		//when
		when(childRepository.save(any(Child.class))).thenReturn(child);
		when(peselService.extractGender(any())).thenReturn(Gender.MALE);
		when(peselService.extractDateOfBirth(any())).thenReturn(LocalDate.of(1994,7,11));
		this.childService.createChild(address,fullName,"94071105694",studyPeriod);

		//then
		verify(childRepository,times(1)).save(
				argThat(arg ->arg.equals(child))
		);
	}

	@Test
	void Should_CreateChildrenWithoutPesel() {
		var studyPeriod = new StudyPeriod(LocalDate.of(2014, Month.SEPTEMBER, 01),
				LocalDate.of(2015, Month.JULY, 26));

		var age = new Age(LocalDate.of(1994,7,11));

		var child = ChildBuilder.aChild()
				.withAge(new Age(LocalDate.of(1994,7,11)))
				.withPeselNumber("NOT_SET")
				.withFullName(fullName)
				.withAddress(address)
				.withStudyPeriod(studyPeriod)
				.withGender(Gender.MALE)
				.withChildStatuses(Set.of(ChildStatus.NEW))
				.build();

		//when
		when(childRepository.save(any(Child.class))).thenReturn(child);
		this.childService.createChild(address,age, fullName,Gender.MALE,studyPeriod);

		//then
		verify(childRepository,times(1)).save(
				argThat(arg ->arg.equals(child))
		);
	}

}
