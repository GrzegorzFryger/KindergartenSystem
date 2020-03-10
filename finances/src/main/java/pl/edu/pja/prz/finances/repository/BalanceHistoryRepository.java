package pl.edu.pja.prz.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.finances.model.BalanceHistory;

import java.util.List;
import java.util.UUID;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Long> {
    List<BalanceHistory> findAllByChildId(UUID childId);
}
