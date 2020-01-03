package pl.edu.pja.prz.payments.facade;

import pl.edu.pja.prz.payments.facade.dto.RecurringPaymentDto;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapper;
import pl.edu.pja.prz.payments.service.RecurringPaymentService;

public class RecurringPaymentFacade {
	private final RecurringPaymentService recurringPaymentService;
	private final RecurringMapper recurringMapper;

	public RecurringPaymentFacade(RecurringPaymentService recurringPaymentService, RecurringMapper recurringMapper) {
		this.recurringPaymentService = recurringPaymentService;
		this.recurringMapper = recurringMapper;
	}

	public RecurringPaymentDto createTuition(RecurringPaymentDto recurringPaymentDto) {
		return recurringMapper.fromRecurringPayment(
				recurringPaymentService.createTuition(
						recurringMapper.toRecurringPayment(recurringPaymentDto)
				)
		);
	}

	public RecurringPaymentDto createOtherPayment(RecurringPaymentDto recurringPaymentDto) {
		return recurringMapper.fromRecurringPayment(
				recurringPaymentService.createOtherPayment(
						recurringMapper.toRecurringPayment(recurringPaymentDto)
				)
		);
	}

	public RecurringPaymentDto updatePayment(RecurringPaymentDto recurringPayment) {
		return recurringMapper.fromRecurringPayment(
				recurringPaymentService.updatePayment(
						recurringMapper.toRecurringPayment(recurringPayment)
				)
		);
	}

	public RecurringPaymentDto markAsCancelPayment(Long paymentId) {
		return recurringMapper.fromRecurringPayment(
				recurringPaymentService.markAsCancelPayment(paymentId)
		);
	}

	public void deletePayment(Long id) {
		recurringPaymentService.deletePayment(id);
	}

}
