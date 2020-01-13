package pl.edu.pja.prz.receivables.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.util.BigDecimalUtils;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private static final String TRANSACTION = "Transaction";

    private final TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
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
        if (repository.findById(transaction.getId()).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION, transaction.getId());
        }
        repository.save(transaction);
    }

    @Override
    public void save(Transaction transaction) {
        if (BigDecimalUtils.isPositive(transaction.getTransactionAmount())) {
            logger.info("Saving transaction: " + transaction.getTitle() + " ["
                    + transaction.getTransactionAmount() + " " + transaction.getTransactionCurrency() + "]");
            repository.save(transaction);
        }
    }
}
