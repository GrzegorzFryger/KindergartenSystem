package pl.edu.pja.prz.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.PaymentFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;
import pl.edu.pja.prz.payments.repository.DiscountRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.util.Set;
import java.util.UUID;

@Component
public class RecurringPaymentServiceImpl implements RecurringPaymentService {

	private final RecurringPaymentRepository recurringPaymentRepository;
	private final DiscountRepository discountRepository;

	@Autowired
	public RecurringPaymentServiceImpl(RecurringPaymentRepository recurringPaymentRepository, DiscountRepository discountRepository) {
		this.recurringPaymentRepository = recurringPaymentRepository;
		this.discountRepository = discountRepository;
	}

	@Override public RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity) {
		return recurringPaymentRepository.save(PaymentFactory.createTuitionPayment(child, payment, periodValidity));
	}

	@Override public RecurringPayment changeTuitionStatus(Long paymentId, Status status) {
		return recurringPaymentRepository.findById(paymentId).map(payment -> {
					payment.setStatus(status);
					return recurringPaymentRepository.save(payment);
				}).orElseThrow(() -> new IllegalArgumentException("Not found payment with id " + paymentId));
	}

	@Override public Set<Discount> addDiscountsToTuition(UUID childId, Long discountId) {
		return menageTuitionDiscounts(childId, discountId, false);
	}

	@Override public Set<Discount> removeDiscountsFromTuition(UUID childId, Long discountId) {
		return menageTuitionDiscounts(childId, discountId, true);
	}

	private Set<Discount> menageTuitionDiscounts(UUID childId, Long discountId, boolean remove) {
		return discountRepository.findById(discountId).map(discount ->
				recurringPaymentRepository.findByChildId(childId).map(
						payment -> {
							if (remove) {
								payment.removeDiscount(discount);
							} else {
								payment.addDiscount(discount);
							}
							recurringPaymentRepository.save(payment);
							return payment.getDiscounts();
						}
				).orElseThrow(() -> new IllegalArgumentException("Not found payment with child id " + childId))
		).orElseThrow(() -> new IllegalArgumentException("Not found discount with id " + discountId));
	}




}
