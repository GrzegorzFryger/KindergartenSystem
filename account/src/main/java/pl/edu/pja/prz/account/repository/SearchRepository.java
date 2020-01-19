package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SearchRepository<T> {
	List<T> findReadOnly(Specification<T> specification, Class<T> classType);
}
