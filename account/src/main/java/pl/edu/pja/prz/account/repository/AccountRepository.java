package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.enums.AccountStatus;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends BasicAccountRepository<Account, UUID> {

	Optional<Account> findByEmailAndAccountStatus(String email, AccountStatus accountStatus);
}
