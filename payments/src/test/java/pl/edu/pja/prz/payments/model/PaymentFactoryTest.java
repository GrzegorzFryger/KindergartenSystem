package pl.edu.pja.prz.payments.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.payments.model.enums.StatusPayment;
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
		var childId = UUID.randomUUID();
		var guardianId = UUID.randomUUID();

		var tuition = new RecurringPayment();
		tuition.setChildId(childId);
		tuition.setGuardianId(guardianId);
		tuition.setDescription(payment.getDescription());
		tuition.setBaseAmount(payment.getBaseAmount());
		tuition.setPeriodValidity(periodValidity);
		tuition.setTypeRecurringPayment(TypeRecurringPayment.TUITION);
        tuition.setStatusPayment(StatusPayment.NONACTIVE);

		//when
		var createdTuition = PaymentFactory.createTuitionPayment(childId, guardianId, payment, periodValidity);
		//then
		assertEquals(tuition, createdTuition);
	}

	@Test
	void should_createOtherRecurringPayment() {
		//given
		var childId = UUID.randomUUID();
		var guardianId = UUID.randomUUID();

		var tuition = new RecurringPayment();
		tuition.setChildId(childId);
		tuition.setGuardianId(guardianId);
		tuition.setDescription(payment.getDescription());
		tuition.setBaseAmount(payment.getBaseAmount());
		tuition.setPeriodValidity(periodValidity);
		tuition.setTypeRecurringPayment(TypeRecurringPayment.OTHER);
        tuition.setStatusPayment(StatusPayment.NONACTIVE);
		//when
		var createdTuition = PaymentFactory.createOtherRecurringPayment(childId, guardianId, payment, periodValidity);
		//then
		assertEquals(tuition, createdTuition);
	}
}