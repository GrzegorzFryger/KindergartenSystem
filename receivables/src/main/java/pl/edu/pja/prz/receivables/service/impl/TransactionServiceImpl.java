package pl.edu.pja.prz.receivables.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.isPositive;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TRANSACTION = "Transaction";

    private final FinancesFacade facade;
    private final TransactionRepository repository;
    private final TransactionMappingService mappingService;

    @Autowired
    public TransactionServiceImpl(FinancesFacade facade, TransactionRepository repository,
                                  TransactionMappingService mappingService) {
        this.facade = facade;
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public Transaction getTransaction(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ElementNotFoundException(TRANSACTION, id));
    }

    @Override
    public List<Transaction> getAllUnassignedTransactions() {
        return repository.findAllByGuardianIdIsNullOrChildIdIsNull();
    }

    @Override
    public List<Transaction> getAllTransactionsByChildId(UUID childId) {
        return repository.findAllByChildId(childId);
    }

    @Override
    public List<Transaction> getAllTransactionsByGuardianId(UUID guardianId) {
        return repository.findAllByGuardianId(guardianId);
    }

    @Override
    public List<Transaction> getAllTransactionsByChildId(UUID childId, LocalDate start, LocalDate end) {
        return repository.findAllByChildIdBetweenDates(childId, start, end);
    }

    @Override
    public List<Transaction> getAllTransactionsByGuardianId(UUID guardianId, LocalDate start, LocalDate end) {
        return repository.findAllByGuardianIdBetweenDates(guardianId, start, end);
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION, id);
        }
        repository.deleteById(id);
    }

    @Override
    public void update(Transaction transaction) {
        //TODO: fix updating method
        //TODO: find way of updating balance using facade (maybe new method in facade?)
        if (repository.findById(transaction.getId()).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION, transaction.getId());
        }
        repository.save(transaction);
    }

    @Override
    public void save(Transaction transaction) {
        if (isPositive(transaction.getTransactionAmount())) {
            mappingService.mapTransaction(transaction);
            repository.save(transaction);
            facade.increaseBalance(transaction.getChildId(),
                    transaction.getTransactionAmount(),
                    transaction.getTitle());
        }
    }
}
