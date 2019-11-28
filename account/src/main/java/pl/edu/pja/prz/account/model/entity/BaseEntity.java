package pl.edu.pja.prz.account.model.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity<T> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private T id;

	@Version
	private Integer version;

	public BaseEntity() {
	}

	public T getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}
}
