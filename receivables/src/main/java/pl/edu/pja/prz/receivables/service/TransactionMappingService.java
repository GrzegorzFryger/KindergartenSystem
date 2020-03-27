package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionMappingService {
    void create(UUID guardianId, UUID childId, String childName, String childSurname);

    Optional<TransactionMapping> getByTitle(String title);

    List<TransactionMapping> getAllByGuardianId(UUID guardianId);

    void update(TransactionMapping transactionMapping);

    void delete(Long id);

    Transaction mapTransaction(Transaction transaction);
}
