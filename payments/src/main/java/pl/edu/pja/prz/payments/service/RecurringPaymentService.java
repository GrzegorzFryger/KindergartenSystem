package pl.edu.pja.prz.payments.service;

import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface RecurringPaymentService {
	RecurringPayment createOtherPayment(RecurringPayment recurringPayment);

	RecurringPayment createOtherPayment(UUID childId, UUID guardianId, Payment payment, PeriodValidity periodValidity);

	RecurringPayment createTuition(RecurringPayment recurringPayment);


	RecurringPayment createTuition(UUID childId, UUID guardianId, Payment payment, PeriodValidity periodValidity);

	RecurringPayment getPaymentById(Long id);

	List<RecurringPayment> getAllPayments();

	RecurringPayment updatePayment(RecurringPayment recurringPayment);

	RecurringPayment markAsCancelPayment(Long paymentId);

	void deletePayment(RecurringPayment recurringPayment);

	void deletePayment(Long id);

	Discount addDiscountToPayment(UUID childId, Long discountId);

	Discount removeDiscountsFromPayment(UUID childId, Long discountId);

	List<RecurringPayment> findAllByChildren(UUID childId);
}
