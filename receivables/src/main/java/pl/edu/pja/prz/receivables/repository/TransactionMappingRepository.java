package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.receivables.model.TransactionMapping;

@Repository
public interface TransactionMappingRepository extends JpaRepository<TransactionMapping, Long> {
    TransactionMapping findByTitle(String title);
}
