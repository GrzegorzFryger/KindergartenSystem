package pl.edu.pja.prz.receivables.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private T id;
	@Version
	private Integer version;

	BaseEntity() {
	}

	public T getId() {
		return id;
	}

}
