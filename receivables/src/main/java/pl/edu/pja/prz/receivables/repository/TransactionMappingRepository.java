package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.receivables.model.TransactionMapping;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionMappingRepository extends JpaRepository<TransactionMapping, Long> {
    TransactionMapping findByTitle(String title);

    List<TransactionMapping> findAllByGuardianId(UUID guardianId);
}
