package pl.edu.pja.prz.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;

@NoRepositoryBean
public interface BasicAccountRepository<E , ID> extends CrudRepository<E, ID> {
	Optional<E> findByEmailAndFullName(String email, FullName fullName);
	Optional<E> findByEmail(String email);
}
