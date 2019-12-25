package pl.edu.pja.prz.payments.model.value;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class Child {
	private UUID childId;
	private FullName fullName;
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
