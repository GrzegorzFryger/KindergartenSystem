package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.payments.model.JobSpecification;

@Repository
public interface JobSpecificationRepository extends JpaRepository<JobSpecification,Long> {
}
