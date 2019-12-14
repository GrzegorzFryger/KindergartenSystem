package pl.edu.pja.prz.tuition.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.tuition.model.enums.TypeRebate;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuitionTest {
	private Tuition tuition;

	@BeforeEach
	void setUp() {
		tuition = new Tuition();
		tuition.setAmount(BigDecimal.valueOf(100));
	}

	@Test
	void shouldSubtractAmountOfDiscount_When_DiscountIsSet() {
		//given
		var rebate1 = new Rebate("Test Discount", BigDecimal.valueOf(50),TypeRebate.AMOUNT);
		var rebate2 = new Rebate("Test Discount", BigDecimal.valueOf(10),TypeRebate.AMOUNT);
		tuition.setRebates(Set.of(rebate1,rebate2));
		//when
		var result = tuition.calculateAmountWithDiscount();
		//then
		assertEquals(BigDecimal.valueOf(40), result);
	}
	@Test
	void shouldSubtractPercentageAmountOfDiscount_When_DiscountIsSet() {
		//given
		var rebate1 = new Rebate("Test Discount", BigDecimal.valueOf(10),TypeRebate.PERCENTAGE);
		var rebate2 = new Rebate("Test Discount", BigDecimal.valueOf(10),TypeRebate.PERCENTAGE);
		tuition.setRebates(Set.of(rebate1,rebate2));
		//when
		var result = tuition.calculateAmountWithDiscount();
		//then
		assertEquals(BigDecimal.valueOf(80F), result);
	}
}