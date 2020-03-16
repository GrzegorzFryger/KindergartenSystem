package pl.edu.pja.prz.receivables.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.math.BigDecimal;
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
        Transaction transaction = repository.findById(id).get();

        repository.deleteById(id);

        facade.decreaseBalance(transaction.getChildId(),
                transaction.getTransactionAmount(),
                "Removed transaction: " + transaction.getTitle());
    }

    @Override
    public void update(Transaction transaction) {
        if (repository.findById(transaction.getId()).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION, transaction.getId());
        }
        BigDecimal balanceToUpdate = BigDecimal.ZERO;
        Transaction transactionToUpdate = repository.findById(transaction.getId()).get();
        if (transaction.getTransactionAmount() != null) {
            balanceToUpdate = transaction.getTransactionAmount()
                    .subtract(transactionToUpdate.getTransactionAmount());
        }
        updateTransactionFields(transactionToUpdate, transaction);

        repository.save(transactionToUpdate);
        applyBalanceCorrections(balanceToUpdate, transaction);
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

    private void updateTransactionFields(Transaction transactionToUpdate, Transaction transaction) {
        if (transaction.getTransactionDate() != null) {
            transactionToUpdate.setTransactionDate(transaction.getTransactionDate());
        }
        if (transaction.getBookingDate() != null) {
            transactionToUpdate.setBookingDate(transaction.getBookingDate());
        }
        if (transaction.getContractorDetails() != null) {
            transactionToUpdate.setContractorDetails(transaction.getContractorDetails());
        }
        if (transaction.getTitle() != null) {
            transactionToUpdate.setTitle(transaction.getTitle());
        }
        if (transaction.getAccountNumber() != null) {
            transactionToUpdate.setAccountNumber(transaction.getAccountNumber());
        }
        if (transaction.getAccountNumber() != null) {
            transactionToUpdate.setBankName(transaction.getBankName());
        }
        if (transaction.getBankName() != null) {
            transactionToUpdate.setDetails(transaction.getDetails());
        }
        if (transaction.getDetails() != null) {
            transactionToUpdate.setTransactionNumber(transaction.getTransactionNumber());
        }
        if (transaction.getTransactionAmount() != null) {
            transactionToUpdate.setTransactionAmount(transaction.getTransactionAmount());
        }
        if (transaction.getTransactionCurrency() != null) {
            transactionToUpdate.setTransactionCurrency(transaction.getTransactionCurrency());
        }
        if (transaction.getChildId() != null) {
            transactionToUpdate.setChildId(transaction.getChildId());
        }
        if (transaction.getGuardianId() != null) {
            transactionToUpdate.setGuardianId(transaction.getGuardianId());
        }
    }

    private void applyBalanceCorrections(BigDecimal balanceToUpdate, Transaction transaction) {
        if (!balanceToUpdate.equals(BigDecimal.ZERO)) {
            facade.applyBalanceCorrection(transaction.getChildId(),
                    transaction.getTransactionAmount(),
                    transaction.getTitle());
        }
    }
}
