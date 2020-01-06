package pl.edu.pja.prz.account.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@FunctionalInterface
public interface SpecificationFunctional {
	Specification appy(Map<String,String> par);
}
