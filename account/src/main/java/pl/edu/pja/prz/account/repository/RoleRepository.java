package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.account.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
