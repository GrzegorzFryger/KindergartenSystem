package pl.edu.pja.prz.payments.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecurringPaymentTest {
	private RecurringPayment recurringPayment;
	private Payment payment;


	@BeforeEach
	void setUp() {
		payment = new Payment(BigDecimal.valueOf(100), "Tuition Payment");
		recurringPayment = new RecurringPayment();
		recurringPayment.setAmount(BigDecimal.valueOf(100));
	}

	@Test
	void shouldSubtractAmountOfDiscount_When_DiscountIsSet() {
		//given
		var rebate1 = new Discount("Test Discount", BigDecimal.valueOf(50), TypeDiscount.AMOUNT);
		rebate1.setId(1L);
		var rebate2 = new Discount("Test Discount", BigDecimal.valueOf(10), TypeDiscount.AMOUNT);
		rebate2.setId(2L);
		recurringPayment.setDiscounts(Set.of(rebate1,rebate2));
		//when
		var result = recurringPayment.calculateAmountWithDiscount();
		//then
		assertEquals(BigDecimal.valueOf(40).setScale(2, RoundingMode.CEILING), result);
	}
	@Test
	void shouldSubtractPercentageAmountOfDiscount_When_DiscountIsSet() {
		//given
		var rebate1 = new Discount("Test Discount", BigDecimal.valueOf(10), TypeDiscount.PERCENTAGE);
		rebate1.setId(1L);
		var rebate2 = new Discount("Test Discount", BigDecimal.valueOf(10), TypeDiscount.PERCENTAGE);
		rebate2.setId(2L);
		recurringPayment.setDiscounts(Set.of(rebate1,rebate2));
		//when
		var result = recurringPayment.calculateAmountWithDiscount();
		//then
		assertEquals(BigDecimal.valueOf(80).setScale(2, RoundingMode.CEILING), result);
	}
}