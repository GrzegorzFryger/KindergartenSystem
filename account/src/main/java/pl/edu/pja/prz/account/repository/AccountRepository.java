package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Account;

@Repository
public interface AccountRepository extends BasicAccountRepository<Account> {
}
