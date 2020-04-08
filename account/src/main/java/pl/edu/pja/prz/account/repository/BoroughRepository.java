package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Borough;

@Repository
public interface BoroughRepository extends JpaRepository<Borough, Long> {
}
