package pl.edu.pja.prz.account.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.account.model.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
