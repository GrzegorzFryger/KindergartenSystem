package pl.edu.pja.prz.payments.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.payments.model.PaymentHistory;
import pl.edu.pja.prz.payments.model.enums.StatusHistoryPayment;
import pl.edu.pja.prz.payments.repository.PaymentHistoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentHistoryService {
    private final PaymentHistoryRepository paymentHistoryRepository;

    public PaymentHistoryService(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public List<PaymentHistory> getAllActiveHistoryOfChild(UUID childId) {
        return this.paymentHistoryRepository.findAllByChildIdAndStatus(childId, StatusHistoryPayment.ACTIVE);
    }
}
