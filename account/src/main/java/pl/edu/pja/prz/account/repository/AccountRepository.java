package pl.edu.pja.prz.account.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.account.model.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
