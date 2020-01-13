package pl.edu.pja.prz.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class BaseEntity<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	@NotNull
	private T id;
	@Version
	private Integer version;

	public BaseEntity() {

	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
