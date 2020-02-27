package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Account;

import java.util.UUID;

@Repository
public interface AccountRepository extends BasicAccountRepository<Account, UUID> {
}
