package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByChildId(UUID childId);
    List<Transaction> findAllByGuardianId(UUID guardianId);
    List<Transaction> findAllByGuardianIdIsNullOrChildIdIsNull();
}
