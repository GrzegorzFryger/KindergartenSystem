package pl.edu.pja.prz.payments.service;

import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface RecurringPaymentService {
	RecurringPayment createOtherPayment(RecurringPayment recurringPayment);

	RecurringPayment createOtherPayment(Child child, Payment payment, PeriodValidity periodValidity);

	RecurringPayment createTuition(RecurringPayment recurringPayment);

	RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity);

	RecurringPayment getPaymentById(Long id);

	List<RecurringPayment> getAllPayments();

	RecurringPayment updatePayment(RecurringPayment recurringPayment);

	RecurringPayment markAsCancelPayment(Long paymentId);

	void deletePayment(RecurringPayment recurringPayment);

	void deletePayment(Long id);

	Set<Discount> addDiscountsToPayment(UUID childId, Long discountId);

	Set<Discount> removeDiscountsFromPayment(UUID childId, Long discountId);
}
