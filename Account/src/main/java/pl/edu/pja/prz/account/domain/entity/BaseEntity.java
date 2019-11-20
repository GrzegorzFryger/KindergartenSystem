package pl.edu.pja.prz.account.domain.entity;

public class BaseEntity<T> {
	private T id;
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
