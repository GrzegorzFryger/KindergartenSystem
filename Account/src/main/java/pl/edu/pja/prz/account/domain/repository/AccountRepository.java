package pl.edu.pja.prz.account.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.account.domain.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
