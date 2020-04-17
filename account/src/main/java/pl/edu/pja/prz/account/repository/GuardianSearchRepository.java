package pl.edu.pja.prz.account.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.prz.account.model.Guardian;

import java.util.List;
import java.util.UUID;

public interface GuardianSearchRepository extends SearchRepository<Guardian> {
    @Transactional(readOnly = true)
    List<Guardian> findByChildId(UUID childId);
}
