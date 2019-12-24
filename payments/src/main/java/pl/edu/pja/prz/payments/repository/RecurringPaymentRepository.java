package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.payments.model.RecurringPayment;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecurringPaymentRepository extends CrudRepository<RecurringPayment, Long> {
	@Query(" FROM #{#entityName} WHERE childId = ?1 AND status NOT LIKE CANCELED ")
	Optional<RecurringPayment> findActiveByChildId(UUID childId);
}

