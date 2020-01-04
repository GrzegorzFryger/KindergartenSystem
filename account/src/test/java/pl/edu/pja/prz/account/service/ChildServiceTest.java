package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.ChildBuilder;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.PeselService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChildServiceTest {
	private Address address;
	private Phone phone;
	private FullName fullName;

	@Mock
	private BoroughService boroughService;
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
		childService = new ChildServiceImpl(childRepository,peselService,boroughService);
	}

	@Test
	void should_createChild() {
		//given
		var borough = new Borough("Testborought", address, phone, "test@wp.com", "975456466");
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withBorough(borough)
				.build();

		//when
		when(boroughService.find(any(Long.class))).thenReturn(Optional.of(borough));
		when(peselService.extractDateOfBirth(any())).thenReturn(LocalDate.now());
		when(peselService.extractGender(any())).thenReturn(Gender.MALE);
		when(childRepository.save(any(Child.class))).thenReturn(child);
		var createdChild = childService.createChild(1L, address, fullName, "00440758725", new StudyPeriod());

		//then
		verify( boroughService, times(1)).find(any(Long.class));
		verify( boroughService, times(1)).addChildToBorough(any(Child.class),any(Borough.class));
	}

	@Test
	void getChildById() {
		//given
		var id = UUID.randomUUID();

		var borough = new Borough("Testborought", address, phone, "test@wp.com", "975456466");
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withBorough(borough)
				.build();


		//when
		when(childRepository.findById(id)).thenReturn(Optional.ofNullable(child));
		var returnedObj = childService.getChildById(id);

		//
		verify(childRepository, times(1)).findById(id);
	}
}