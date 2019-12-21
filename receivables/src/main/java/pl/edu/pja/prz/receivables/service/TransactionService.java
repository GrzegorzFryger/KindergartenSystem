package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction getTransaction(Long id);

    List<Transaction> getAllTransactions();

    void delete(Long id);

    void update(Transaction transaction);

    void create(Transaction transaction);
}
