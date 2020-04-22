package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction getTransaction(Long id);

    List<Transaction> getAllUnassignedTransactions();

    List<Transaction> getAllTransactionsByChildId(UUID childId);

    List<Transaction> getAllTransactionsByGuardianId(UUID guardianId);

    List<Transaction> getAllTransactionsByChildId(UUID childId, LocalDate start, LocalDate end);

    List<Transaction> getAllTransactionsByGuardianId(UUID guardianId, LocalDate start, LocalDate end);

    List<Transaction> getAllTransactionsFromPastMonth(LocalDate start, LocalDate end);

    void delete(Long id);

    void update(Transaction transaction);

    void save(Transaction transaction);
}
