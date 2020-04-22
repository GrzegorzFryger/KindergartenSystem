package pl.edu.pja.prz.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.finances.model.AccountNumber;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long> {
}
