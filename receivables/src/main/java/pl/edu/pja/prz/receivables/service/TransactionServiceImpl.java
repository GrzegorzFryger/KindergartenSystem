package pl.edu.pja.prz.receivables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
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
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
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
    public void create(Transaction transaction) {
        repository.save(transaction);
    }
}
