package pl.edu.pja.prz.payments.service;

import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.util.Set;
import java.util.UUID;


public interface RecurringPaymentService {
	RecurringPayment updatePayment(Long paymentId, Payment newPayment, PeriodValidity period, Status status);

	RecurringPayment createOtherPayment(Child child, Payment payment, PeriodValidity periodValidity);

	RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity);

	void markAsCancelPayment(Long paymentId);

	void deletePayment(RecurringPayment recurringPayment);

	Set<Discount> addDiscountsToPayment(UUID childId, Long discountId);

	Set<Discount> removeDiscountsFromPayment(UUID childId, Long discountId);
}
