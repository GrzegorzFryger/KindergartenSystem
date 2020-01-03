package pl.edu.pja.prz.payments.model;

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

	public void setId(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

}
