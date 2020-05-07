package pl.edu.pja.prz.payments.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.facade.mapper.PaymentHistoryMapper;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;
import pl.edu.pja.prz.payments.service.PaymentHistoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PaymentHistoryFacade {
    private final PaymentHistoryService paymentHistoryService;
    private final PaymentHistoryMapper paymentHistoryMapper;

    public PaymentHistoryFacade(PaymentHistoryService paymentHistoryService, PaymentHistoryMapper paymentHistoryMapper) {
        this.paymentHistoryService = paymentHistoryService;
        this.paymentHistoryMapper = paymentHistoryMapper;
    }

    public List<PaymentHistoryDto> getAllHistoryOfChild(UUID childId) {
        return this.paymentHistoryService.getAllHistoryOfChild(childId)
                .stream()
                .map(this.paymentHistoryMapper::fromPaymentHistory)
                .collect(Collectors.toList());
    }

}
