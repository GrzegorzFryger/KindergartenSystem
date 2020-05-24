package pl.edu.pja.prz.payments.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapper;
import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;
import pl.edu.pja.prz.payments.service.RecurringPaymentService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RecurringPaymentFacadeImpl implements RecurringPaymentFacade {
    private final RecurringPaymentService recurringPaymentService;
    private final RecurringMapper recurringMapper;

    public RecurringPaymentFacadeImpl(RecurringPaymentService recurringPaymentService, RecurringMapper recurringMapper) {
        this.recurringPaymentService = recurringPaymentService;
        this.recurringMapper = recurringMapper;
    }

    @Override
    public RecurringPaymentDto createTuition(RecurringPaymentDto recurringPaymentDto) {
        return recurringMapper.fromRecurringPayment(
                recurringPaymentService.createTuition(
                        recurringMapper.toRecurringPayment(recurringPaymentDto)
                )
        );
    }

    @Override
    public RecurringPaymentDto createOtherPayment(RecurringPaymentDto recurringPaymentDto) {
        return recurringMapper.fromRecurringPayment(
                recurringPaymentService.createOtherPayment(
                        recurringMapper.toRecurringPayment(recurringPaymentDto)
                )
        );
    }

    @Override
    public RecurringPaymentDto findById(Long id) {
        return recurringMapper.fromRecurringPayment(
                recurringPaymentService.getPaymentById(id)
        );
    }

    @Override
    public List<RecurringPaymentDto> findAllPayments() {
        return recurringPaymentService.getAllPayments()
                .stream()
                .map(recurringMapper::fromRecurringPayment)
                .collect(Collectors.toList());
    }

    @Override
    public RecurringPaymentDto updatePayment(RecurringPaymentDto recurringPayment) {
        return recurringMapper.fromRecurringPayment(
                recurringPaymentService.updatePayment(
                        recurringMapper.toRecurringPayment(recurringPayment)
                )
        );
    }

    @Override
    public RecurringPaymentDto markAsCancelPayment(Long paymentId) {
        return recurringMapper.fromRecurringPayment(
                recurringPaymentService.markAsCancelPayment(paymentId)
        );
    }

    @Override
    public void deletePayment(Long id) {
        recurringPaymentService.deletePayment(id);
    }

    @Override
    public List<RecurringPaymentDto> findAllByChild(UUID childId) {
        return this.recurringPaymentService.findAllByChildren(childId)
                .stream()
                .map(this.recurringMapper::fromRecurringPayment)
                .collect(Collectors.toList());
    }
}
