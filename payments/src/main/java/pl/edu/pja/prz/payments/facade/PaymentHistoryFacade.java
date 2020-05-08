package pl.edu.pja.prz.payments.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.facade.mapper.PaymentHistoryMapper;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;
import pl.edu.pja.prz.payments.service.PaymentDebitService;
import pl.edu.pja.prz.payments.service.PaymentHistoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PaymentHistoryFacade {
    private final PaymentHistoryService paymentHistoryService;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final PaymentDebitService paymentDebitService;

    public PaymentHistoryFacade(PaymentHistoryService paymentHistoryService, PaymentHistoryMapper paymentHistoryMapper,
                                PaymentDebitService paymentDebitService) {
        this.paymentHistoryService = paymentHistoryService;
        this.paymentHistoryMapper = paymentHistoryMapper;
        this.paymentDebitService = paymentDebitService;
    }

    public List<PaymentHistoryDto> getAllHistoryOfChild(UUID childId) {
        return this.paymentHistoryService.getAllHistoryOfChild(childId)
                .stream()
                .map(this.paymentHistoryMapper::fromPaymentHistory)
                .collect(Collectors.toList());
    }

    public void applyBalanceCorrectionForPayment(PaymentHistoryDto paymentHistory) {
        this.paymentDebitService.applyBalanceCorrectionForPayment(
                this.paymentHistoryMapper.toPaymentHistory(paymentHistory)
        );
    }

}
