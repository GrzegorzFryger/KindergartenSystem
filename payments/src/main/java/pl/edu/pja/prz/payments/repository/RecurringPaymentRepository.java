package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecurringPaymentRepository extends JpaRepository<RecurringPayment, Long> {
	@Query(" FROM #{#entityName} WHERE childId = ?1 AND status NOT LIKE CANCELED ")
	Optional<RecurringPayment> findActiveByChildId(UUID childId);

	List<RecurringPayment> findAllByStatus(Status status);
}

