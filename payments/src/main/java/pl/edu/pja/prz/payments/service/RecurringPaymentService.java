package pl.edu.pja.prz.payments.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.util.Set;
import java.util.UUID;

@Service
public interface RecurringPaymentService {
	RecurringPayment updateRecurringPayment(Long paymentId, Payment newPayment, PeriodValidity period, Status status);

	RecurringPayment createOtherPayment(Child child, Payment payment, PeriodValidity periodValidity);

	RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity);

	void deleteRecurringPayment(Long paymentId);

	Set<Discount> addDiscountsToRecurringPayment(UUID childId, Long discountId);

	Set<Discount> removeDiscountsFromRecurringPayment(UUID childId, Long discountId);
}
