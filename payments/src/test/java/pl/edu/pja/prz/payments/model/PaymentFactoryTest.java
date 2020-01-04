package pl.edu.pja.prz.payments.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentFactoryTest {
	private Child child;
	private Payment payment;
	private PeriodValidity periodValidity;

	@BeforeEach
	void setUp() {

		child = new Child(new UUID(1L, 2L), new UUID(3L, 4L));
		payment = new Payment(new BigDecimal("50.0"), "Test payment");
		periodValidity = new PeriodValidity(LocalDate.now(), LocalDate.now());
	}

	@Test
	void should_createTuitionPayment() {
		//given
		var tuition = new RecurringPayment(child,
				payment,
				periodValidity,
				TypeRecurringPayment.TUITION,
				Status.NONACTIVE
		);
		//when
		var createdTuition = PaymentFactory.createTuitionPayment(child, payment, periodValidity);
		//then
		assertEquals(tuition, createdTuition);
	}

	@Test
	void should_createOtherRecurringPayment() {
		//given
		var tuition = new RecurringPayment(child,
				payment,
				periodValidity,
				TypeRecurringPayment.OTHER,
				Status.NONACTIVE
		);
		//when
		var createdTuition = PaymentFactory.createOtherRecurringPayment(child, payment, periodValidity);
		//then
		assertEquals(tuition, createdTuition);
	}
}