package pl.edu.pja.prz.commons.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntityLong implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private Integer version;

	public BaseEntityLong() {

	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return version != null ? version.equals(that.version) : that.version == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (version != null ? version.hashCode() : 0);
		return result;
	}
}
