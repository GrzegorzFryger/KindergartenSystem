package pl.edu.pja.prz.account.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntityLong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private Integer version;

	public BaseEntityLong() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseEntityLong)) return false;

		BaseEntityLong that = (BaseEntityLong) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		return getVersion() != null ? getVersion().equals(that.getVersion()) : that.getVersion() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
		return result;
	}
}
