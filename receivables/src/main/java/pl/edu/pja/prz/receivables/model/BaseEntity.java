package pl.edu.pja.prz.receivables.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private T id;
	@Version
	private Integer version;

	BaseEntity() {
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
