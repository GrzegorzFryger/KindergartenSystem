package pl.edu.pja.prz.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.value.FullName;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BasicAccountRepository<T extends Account> extends CrudRepository<T,UUID> {
	Optional<T> findByEmailAndFullName(String email, FullName fullName);
	Optional<T> findByEmail(String email);

}
