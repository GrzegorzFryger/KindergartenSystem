package pl.edu.pja.prz.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.finances.model.BalanceHistory;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Long> {

}
