package pl.edu.pja.prz.receivables.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.util.RandomUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionMappingServiceImpl implements TransactionMappingService {
    private static final String TRANSACTION_MAPPING = "Transaction Mapping";
    @Value("${title-mapping.length}")
    private Integer titleMappingLength;

    private final TransactionMappingRepository repository;

    @Autowired
    public TransactionMappingServiceImpl(TransactionMappingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(UUID guardianId, UUID childId) {
        TransactionMapping mapping = new TransactionMapping();
        mapping.setGuardianId(guardianId);
        mapping.setChildId(childId);
        while (true) {
            String title = RandomUtils.randomNumeric(titleMappingLength);
            if (getByTitle(title).isEmpty()) {
                mapping.setTitle(title);
                break;
            }
        }
        repository.save(mapping);
    }

    @Override
    public Optional<TransactionMapping> getByTitle(String title) {
        TransactionMapping mapping = repository.findByTitle(title);
        return Optional.ofNullable(mapping);
    }

    @Override
    public void update(TransactionMapping transactionMapping) {
        if (getByTitle(transactionMapping.getTitle()).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION_MAPPING, transactionMapping.getTitle());
        }
        repository.save(transactionMapping);
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException(TRANSACTION_MAPPING, id);
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
