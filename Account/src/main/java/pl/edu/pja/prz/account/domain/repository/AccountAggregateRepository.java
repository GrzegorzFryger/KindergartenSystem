package pl.edu.pja.prz.account.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.account.domain.AccountAggregate;

public interface AccountAggregateRepository extends CrudRepository<AccountAggregate, Long> {
}
