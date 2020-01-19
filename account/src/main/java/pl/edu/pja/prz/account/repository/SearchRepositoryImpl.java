package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SearchRepositoryImpl<T> implements SearchRepository<T> {
	@PersistenceContext(unitName = "accountModuleEntityManagerFactory")
	private EntityManager entityManager;

	public SearchRepositoryImpl() {
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findReadOnly(Specification<T> specification, Class<T> classType) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(classType);
		Root<T> root = criteria.from(classType);

		criteria.where(specification.toPredicate(root, criteria, builder));
		return entityManager.createQuery(criteria).getResultList();
	}


}
