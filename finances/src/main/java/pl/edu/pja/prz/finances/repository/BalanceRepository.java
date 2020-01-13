package pl.edu.pja.prz.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.finances.model.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

}
