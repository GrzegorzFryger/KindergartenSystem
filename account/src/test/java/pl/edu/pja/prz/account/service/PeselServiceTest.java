package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.value.Gender;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeselServiceTest {

	private PeselService peselService;

	@BeforeEach
	void setUp() {
		this.peselService = new PeselServiceImpl();
	}

	@Test
	public void Should_ConvertPasselNumberToDate_When_NumberOfPeselIsCorrect() {
		//given
		var peselNumberBetween1900_1999 = "70020556117";
		var peselNumberBetween2000_2099 = "06320538371";
		var peselNumberBetween2100_2199 = "00440758725";
		//when
		var date1900 = peselService.extractDateOfBirth(peselNumberBetween1900_1999);
		var date2000 = peselService.extractDateOfBirth(peselNumberBetween2000_2099);
		var date2100 = peselService.extractDateOfBirth(peselNumberBetween2100_2199);
		//then
		assertEquals(LocalDate.of(1970, 2, 5), date1900);
		assertEquals(LocalDate.of(2006, 12, 5), date2000);
		assertEquals(LocalDate.of(2100, 4, 7), date2100);
	}

	@Test
	public void Should_ConvertPasselNumberToGender_When_NumberOfPeselIsCorrect() {
		//given
		var menPeselNumber = "70020556117";
		var womenPeselNumber = "20240438164";

		//when
		var menGender = peselService.extractGender(menPeselNumber);
		var womanGender = peselService.extractGender(womenPeselNumber);

		//then
		assertEquals(Gender.MALE, menGender);
		assertEquals(Gender.FEMALE, womanGender);

	}

}