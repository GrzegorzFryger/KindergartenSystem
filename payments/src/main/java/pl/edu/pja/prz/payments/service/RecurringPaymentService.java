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
	RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity);

	RecurringPayment changeTuitionStatus(Long paymentId, Status status);

	Set<Discount> addDiscountsToTuition(UUID childId, Long discountId);

	Set<Discount> removeDiscountsFromTuition(UUID childId, Long discountId);
}
