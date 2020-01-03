package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.receivables.model.CashPayment;

public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
}
