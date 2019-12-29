package pl.edu.pja.prz.receivables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.receivables.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
