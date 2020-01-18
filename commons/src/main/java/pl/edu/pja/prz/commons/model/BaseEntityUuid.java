package pl.edu.pja.prz.commons.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntityUuid implements BaseEntity<UUID> {
	@Id
	@GeneratedValue()
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID id;
	@Version
	private Integer version;

	public BaseEntityUuid() {
	}

	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseEntityUuid)) return false;

		BaseEntityUuid that = (BaseEntityUuid) o;

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
