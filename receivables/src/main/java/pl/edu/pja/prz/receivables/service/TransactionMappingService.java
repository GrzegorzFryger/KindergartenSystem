package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;

import java.util.Optional;

public interface TransactionMappingService {
    void create(TransactionMapping transactionMapping);

    Optional<TransactionMapping> getByTitle(String title);

    void update(TransactionMapping transactionMapping);

    void delete(Long id);

    Transaction mapTransaction(Transaction transaction);
}
