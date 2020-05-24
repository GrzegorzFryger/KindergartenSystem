package pl.edu.pja.prz.payments.facade;

import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;

import java.util.List;
import java.util.UUID;

public interface RecurringPaymentFacade {
	RecurringPaymentDto createTuition(RecurringPaymentDto recurringPaymentDto);

	RecurringPaymentDto createOtherPayment(RecurringPaymentDto recurringPaymentDto);

	RecurringPaymentDto findById(Long id);

	List<RecurringPaymentDto> findAllPayments();

	RecurringPaymentDto updatePayment(RecurringPaymentDto recurringPayment);

	RecurringPaymentDto markAsCancelPayment(Long paymentId);

	void deletePayment(Long id);

	List<RecurringPaymentDto> findAllByChild(UUID childId);
}
