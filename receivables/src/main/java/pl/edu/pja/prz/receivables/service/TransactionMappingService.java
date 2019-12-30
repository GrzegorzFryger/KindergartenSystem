package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.TransactionMapping;

public interface TransactionMappingService {
    void create(TransactionMapping transactionMapping);

    TransactionMapping getByTitle(String title);

    void update(TransactionMapping transactionMapping);

    void delete(Long id);
}
