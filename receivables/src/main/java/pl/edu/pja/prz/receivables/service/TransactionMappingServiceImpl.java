package pl.edu.pja.prz.receivables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;

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
    public TransactionMapping getByTitle(String title) {
        TransactionMapping mapping = repository.findByTitle(title);
        if (mapping == null) {
            throw new NullPointerException("Transaction mapping with title: " + title + " not found");
        }
        return mapping;
    }

    @Override
    public void update(TransactionMapping transactionMapping) {
        if (getByTitle(transactionMapping.getTitle()) != null) {
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
}
