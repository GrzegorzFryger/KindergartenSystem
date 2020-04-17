package pl.edu.pja.prz.account.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Guardian_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class GuardianSearchRepositoryImpl extends SearchRepositoryImpl<Guardian> implements GuardianSearchRepository {

    public GuardianSearchRepositoryImpl() {
        super();
    }

    @Transactional(readOnly = true)
    public List<Guardian> findByChildId(UUID childId) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Guardian> criteria = builder.createQuery(Guardian.class);
        Root<Guardian> rootGuardian = criteria.from(Guardian.class);
        Join<Guardian, Child> childJoin = rootGuardian.join(Guardian_.CHILDREN);
        criteria.where(builder.equal(childJoin.get(Guardian_.id), childId));

        return entityManager.createQuery(criteria).getResultList();
    }
}
