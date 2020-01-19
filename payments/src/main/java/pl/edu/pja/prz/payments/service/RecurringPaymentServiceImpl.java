package pl.edu.pja.prz.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.PaymentFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;
import pl.edu.pja.prz.payments.repository.DiscountRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RecurringPaymentServiceImpl implements RecurringPaymentService  {
	private final RecurringPaymentRepository recurringPaymentRepository;
	private final DiscountRepository discountRepository;

	@Autowired
	public RecurringPaymentServiceImpl(RecurringPaymentRepository recurringPaymentRepository,
	                                   DiscountRepository discountRepository) {
		this.recurringPaymentRepository = recurringPaymentRepository;
		this.discountRepository = discountRepository;
	}

	@Override
	public RecurringPayment createOtherPayment(RecurringPayment recurringPayment) {
		return this.createOtherPayment(recurringPayment.getChild(),recurringPayment,recurringPayment.getPeriodValidity());
	}

	@Override
	public RecurringPayment createOtherPayment(Child child, Payment payment, PeriodValidity periodValidity) {
		return recurringPaymentRepository
				.save(PaymentFactory.createOtherRecurringPayment(child, payment, periodValidity));
	}

	@Override
	public RecurringPayment createTuition(RecurringPayment recurringPayment) {
		return this.createTuition(recurringPayment.getChild(),recurringPayment,recurringPayment.getPeriodValidity());
	}

	@Override
	public RecurringPayment createTuition(Child child, Payment payment, PeriodValidity periodValidity) {
		return recurringPaymentRepository.save(PaymentFactory.createTuitionPayment(child, payment, periodValidity));
	}

	@Override
	public RecurringPayment getPaymentById(Long id) {
		return recurringPaymentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not found payment with id " + id));
	}

	@Override
	public List<RecurringPayment> getAllPayments() {
		return recurringPaymentRepository.findAll();
	}


	@Override
	public RecurringPayment updatePayment(RecurringPayment recurringPayment) {
		return recurringPaymentRepository.findById(recurringPayment.getId()).map(payment -> {
			if (recurringPayment.getDiscounts() != null) {
				payment.setBaseAmount(recurringPayment.getBaseAmount());
			}
			if(recurringPayment.getDescription() != null) {
				payment.setDescription(recurringPayment.getDescription());
			}
			if (recurringPayment.getPeriodValidity() != null) {
				payment.setPeriodValidity(recurringPayment.getPeriodValidity());
			}
			if (recurringPayment.getStatus() != null) {
				payment.setStatus(recurringPayment.getStatus());
			}
			return recurringPaymentRepository.save(payment);
		}).orElseThrow(() -> new IllegalArgumentException("Not found payment with id " + recurringPayment.getId()));
	}

	@Override
	public RecurringPayment markAsCancelPayment(Long paymentId) {
		return recurringPaymentRepository.findById(paymentId).map(payment -> {
			payment.setStatus(Status.CANCELED);
			return recurringPaymentRepository.save(payment);
		}).orElseThrow(() -> {throw new IllegalArgumentException("Not found payment with id " + paymentId);});
	}

	@Override
	public void deletePayment(RecurringPayment recurringPayment) {
		recurringPaymentRepository.delete(recurringPayment);
	}

	@Override
	public void deletePayment(Long id) {
		recurringPaymentRepository.deleteById(id);
	}

	@Override
	public Set<Discount> addDiscountsToPayment(UUID childId, Long discountId) {
		return menageRecurringPaymentDiscounts(childId, discountId, false);
	}

	@Override
	public Set<Discount> removeDiscountsFromPayment(UUID childId, Long discountId) {
		return menageRecurringPaymentDiscounts(childId, discountId, true);
	}

	private Set<Discount> menageRecurringPaymentDiscounts(UUID childId, Long discountId, boolean remove) {
		return discountRepository.findById(discountId).map(discount ->
				recurringPaymentRepository.findActiveByChildId(childId).map(
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
