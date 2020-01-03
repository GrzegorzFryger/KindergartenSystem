package pl.edu.pja.prz.payments.facade;

import pl.edu.pja.prz.payments.facade.dto.RecurringPaymentDto;

import java.util.List;

public interface RecurringPaymentFacade {
	RecurringPaymentDto createTuition(RecurringPaymentDto recurringPaymentDto);

	RecurringPaymentDto createOtherPayment(RecurringPaymentDto recurringPaymentDto);

	RecurringPaymentDto findById(Long id);

	List<RecurringPaymentDto> findAllPayments();

	RecurringPaymentDto updatePayment(RecurringPaymentDto recurringPayment);

	RecurringPaymentDto markAsCancelPayment(Long paymentId);

	void deletePayment(Long id);
}
