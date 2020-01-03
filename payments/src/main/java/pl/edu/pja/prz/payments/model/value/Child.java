package pl.edu.pja.prz.payments.model.value;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class Child {

	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID childId;
	private FullName fullName;
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID guardianId;

	Child() {
	}

	public Child(UUID childId, FullName fullName, UUID guardianId) {
		this.childId = childId;
		this.fullName = fullName;
		this.guardianId = guardianId;
	}

	public UUID getChildId() {
		return childId;
	}

	public UUID getGuardianId() {
		return guardianId;
	}

	public FullName getFullName() {
		return fullName;
	}
}
