package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

public class PaymentFactory {
	private static final Status STATUS = Status.NONACTIVE;

	public static RecurringPayment createTuitionPayment(Child child, Payment payment, PeriodValidity periodValidity) {
		return new RecurringPayment(child, payment, periodValidity, TypeRecurringPayment.TUITION, STATUS);
	}

	public static RecurringPayment createOtherRecurringPayment(Child child, Payment payment, PeriodValidity periodValidity) {
		return new RecurringPayment(child, payment, periodValidity, TypeRecurringPayment.OTHER, STATUS);
	}


}
