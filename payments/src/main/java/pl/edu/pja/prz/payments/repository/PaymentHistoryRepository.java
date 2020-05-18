package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.payments.model.PaymentHistory;
import pl.edu.pja.prz.payments.model.enums.StatusHistoryPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    List<PaymentHistory> findAllByChildIdAndStatus(UUID childId, StatusHistoryPayment statusHistoryPayment);

    List<PaymentHistory> findAllByDateBetween(LocalDate start, LocalDate end);
}
