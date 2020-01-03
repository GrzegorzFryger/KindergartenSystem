package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByChildId(UUID childId);
    List<Transaction> findAllByGuardianId(UUID guardianId);
    List<Transaction> findAllByGuardianIdIsNullOrChildIdIsNull();
}
