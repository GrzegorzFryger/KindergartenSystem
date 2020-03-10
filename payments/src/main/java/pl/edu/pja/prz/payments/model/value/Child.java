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
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID guardianId;

	public Child() {
	}

	public Child(UUID childId, UUID guardianId) {
		this.childId = childId;
		this.guardianId = guardianId;
	}

	public UUID getChildId() {
		return childId;
	}

	public UUID getGuardianId() {
		return guardianId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public void setGuardianId(UUID guardianId) {
		this.guardianId = guardianId;
	}
}
