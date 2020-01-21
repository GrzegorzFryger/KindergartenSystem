package pl.edu.pja.prz.finances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.finances.model.Balance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> getByChildId(UUID childId);

    List<Balance> getAllByGuardianId(UUID guardianId);
}
