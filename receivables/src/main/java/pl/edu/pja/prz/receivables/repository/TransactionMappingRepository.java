package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.receivables.model.TransactionMapping;

public interface TransactionMappingRepository extends JpaRepository<TransactionMapping, Long> {
    TransactionMapping findByTitle(String title);
}
