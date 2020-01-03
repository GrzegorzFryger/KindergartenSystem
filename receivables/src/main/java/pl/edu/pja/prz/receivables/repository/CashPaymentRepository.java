package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.receivables.model.CashPayment;

import java.util.List;
import java.util.UUID;

public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
    List<CashPayment> findAllByGuardianId(UUID guardianId);

    List<CashPayment> findAllByChildId(UUID childId);
}
