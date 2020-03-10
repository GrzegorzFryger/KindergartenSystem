package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;

import java.util.Optional;
import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID>, SearchRepository<Child> {
	 Optional<Child> findAllByGuardians(Guardian guardian);
}
