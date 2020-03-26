package pl.edu.pja.prz.receivables.service.impl;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.event.AppendChildEvent;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.event.AppendChildListener;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.repository.TransactionMappingRepository;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.util.RandomUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionMappingServiceImpl implements TransactionMappingService, AppendChildListener {
	private final Logger logger = LoggerFactory.logger(TransactionMappingServiceImpl.class);
	private static final String TRANSACTION_MAPPING = "Transaction Mapping";

	@Value("${title-mapping.length}")
	private Integer titleMappingLength;

	private final TransactionMappingRepository repository;

	@Autowired
	public TransactionMappingServiceImpl(TransactionMappingRepository repository) {
		this.repository = repository;
	}

	@Override
	public void create(UUID guardianId, UUID childId, String childName, String childSurname) {
		TransactionMapping mapping = new TransactionMapping();
		mapping.setGuardianId(guardianId);
		mapping.setChildId(childId);
		mapping.setChildName(childName);
		mapping.setChildSurname(childSurname);

		LocalDate localDateTime = LocalDate.now();
		String dateString = localDateTime.toString().replace("-", "");
		while (true) {
			String title = dateString + RandomUtils.randomNumeric(titleMappingLength);
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
	public List<TransactionMapping> getAllByGuardianId(UUID guardianId) {
		return repository.findAllByGuardianId(guardianId);
	}

	@Override
	public void update(TransactionMapping transactionMapping) {
		if (getByTitle(transactionMapping.getTitle()).isEmpty()) {
			throw new ElementNotFoundException(TRANSACTION_MAPPING, transactionMapping.getTitle());
		}
		TransactionMapping transactionMappingToUpdate = getByTitle(transactionMapping.getTitle()).get();
		transactionMappingToUpdate.setTitle(transactionMapping.getTitle());
		transactionMappingToUpdate.setChildId(transactionMapping.getChildId());
		transactionMappingToUpdate.setGuardianId(transactionMapping.getGuardianId());
		transactionMappingToUpdate.setChildName(transactionMapping.getChildName());
		transactionMappingToUpdate.setChildSurname(transactionMapping.getChildSurname());

		repository.save(transactionMappingToUpdate);
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

	@Override
	public void onApplicationEvent(AppendChildEvent appendChildEvent) {
		this.logger.infof("Receive AppendChildEvent with guardian id - {} and child id - {} ",
				appendChildEvent.getGuardianChildDependency().getGuardianId(),
				appendChildEvent.getGuardianChildDependency().getChildId()
		);

		if (appendChildEvent.getGuardianChildDependency() != null) {
			var dependency = appendChildEvent.getGuardianChildDependency();
			this.create(dependency.getGuardianId(), dependency.getChildId(),
					dependency.getChildFullName().getName(), dependency.getChildFullName().getSurname());
		}
	}
}
