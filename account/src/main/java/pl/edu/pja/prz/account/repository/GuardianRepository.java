package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Guardian;

@Repository
public interface GuardianRepository extends BasicAccountRepository<Guardian>, SearchRepository<Guardian> {
}
