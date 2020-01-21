package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ChildMapperTest {
	private ChildMapper childMapper;

	private Child child;
	private ChildDto childDto;

	private FullName fullName;
	private Address address;
	private StudyPeriod studyPeriod;
	private Age ageGiven;
	private String pesel;
	private Gender gender;
	private Borough borough;

	@BeforeEach
	public void setUp() {
		this.childMapper = Mappers.getMapper(ChildMapper.class);

		child = new Child();
		childDto = new ChildDto();
		borough = new Borough();

		fullName = new FullName("TestName", "TestSurname");
		address = new Address("70-700", "City", "Street 256");
		studyPeriod = new StudyPeriod(LocalDate.of(2014, Month.SEPTEMBER, 01),
				LocalDate.of(2015, Month.JULY, 26));
		ageGiven = new Age(LocalDate.of(2008, Month.FEBRUARY, 15));
		pesel = "00440758725";
		gender = Gender.MALE;
		borough.setId(1L);

		child.setFullName(fullName);
		child.setAddress(address);
		child.setAge(ageGiven);

		child.setGender(gender);
		child.setPeselNumber(pesel);
		child.setStudyPeriod(studyPeriod);

		childDto.setName(fullName.getName());
		childDto.setSurname(fullName.getSurname());
		childDto.setPostalCode(address.getPostalCode());
		childDto.setCity(address.getCity());
		childDto.setStreetNumber(address.getStreetNumber());
		childDto.setPesel(pesel);
		childDto.setGender(gender);
		childDto.setStartDate(studyPeriod.getAdditionDate());
		childDto.setEndDate(studyPeriod.getEndingDate());
		childDto.setDateOfBirth(ageGiven.getDateOfBirth());

	}

	@Test
	public void Should_MapFromChild() {
		//When
		ChildDto newChildDto = childMapper.fromChild(child);

		//Then
		verifyFromChild(newChildDto);

	}

	@Test
	public void Should_MapToAge() {

		//When
		Age newAge = childMapper.toAge(childDto);
		//Then
		verifyToAge(newAge);
	}

	@Test
	public void Should_MapToAddress() {

		//When
		Address newAddress = childMapper.toAddress(childDto);

		//Then
		verifyAddress(newAddress);

	}

	@Test
	public void Should_MapToStudyPeriod() {
		//When
		StudyPeriod newStudyPeriod = childMapper.toStudyPeriod(childDto);

		//Then
		verifyStudyPeriod(newStudyPeriod);
	}

	@Test
	public void Should_MapToFullName() {
		//When
		FullName newFullName = childMapper.toFullName(childDto);

		//Then
		verifyFullName(newFullName);
	}

	private void verifyFromChild(ChildDto childDto) {
		assertNotNull(childDto);
		assertEquals(fullName.getName(), childDto.getName());
		assertEquals(fullName.getSurname(), childDto.getSurname());
		assertEquals(address.getPostalCode(), childDto.getPostalCode());
		assertEquals(address.getCity(), childDto.getCity());
		assertEquals(address.getStreetNumber(), childDto.getStreetNumber());
		assertEquals(pesel, childDto.getPesel());
		assertEquals(gender, childDto.getGender());
		assertEquals(studyPeriod.getAdditionDate(), childDto.getStartDate());
		assertEquals(studyPeriod.getEndingDate(), childDto.getEndDate());
		assertEquals(ageGiven.getDateOfBirth(), childDto.getDateOfBirth());

	}

	private void verifyToAge(Age age) {
		assertNotNull(age);
		assertEquals(ageGiven, age);
	}

	private void verifyAddress(Address address) {
		assertNotNull(address);
		assertEquals("70-700", address.getPostalCode());
		assertEquals("City", address.getCity());
		assertEquals("Street 256", address.getStreetNumber());
	}

	private void verifyStudyPeriod(StudyPeriod studyPeriod) {
		assertNotNull(studyPeriod);
		assertEquals(LocalDate.of(2014, Month.SEPTEMBER, 01), studyPeriod.getAdditionDate());
		assertEquals(LocalDate.of(2015, Month.JULY, 26), studyPeriod.getEndingDate());
	}

	private void verifyFullName(FullName fullName) {
		assertNotNull(fullName);
		assertEquals("TestName", fullName.getName());
		assertEquals("TestSurname", fullName.getSurname());
	}
}
