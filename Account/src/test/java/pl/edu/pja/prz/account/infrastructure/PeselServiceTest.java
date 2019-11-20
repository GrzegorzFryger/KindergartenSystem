package pl.edu.pja.prz.account.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

		//when
		var date = peselService.generateDateFromPesel(peselNumberBetween1900_1999);
		//then
		assertEquals(LocalDate.of(1970, 2, 5), date);
	}

}