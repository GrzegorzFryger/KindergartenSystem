package pl.edu.pja.prz.receivables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;

import java.util.Optional;

@Service
public class TransactionMappingServiceImpl implements TransactionMappingService {
    private final TransactionMappingRepository repository;

    @Autowired
    public TransactionMappingServiceImpl(TransactionMappingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(TransactionMapping transactionMapping) {
        repository.save(transactionMapping);
    }

    @Override
    public Optional<TransactionMapping> getByTitle(String title) {
        TransactionMapping mapping = repository.findByTitle(title);
        return Optional.ofNullable(mapping);
    }

    @Override
    public void update(TransactionMapping transactionMapping) {
        if (getByTitle(transactionMapping.getTitle()).isPresent()) {
            repository.save(transactionMapping);
        }
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NullPointerException("Element with id: " + id + " is not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Transaction mapTransaction(Transaction transaction) {
        Optional<TransactionMapping> mapping = getByTitle(transaction.getTitle());

        if (mapping.isPresent()) {
            transaction.setGuardianId(mapping.get().getGuardianId());
            transaction.setChildId(mapping.get().getChildId());
        }
        return transaction;
    }
}
