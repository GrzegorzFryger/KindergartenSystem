package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.value.FullName;

import java.util.UUID;

public class Child {
	private UUID childId;
	private UUID guardianId;
	private FullName fullName;

	Child() {
	}

	public Child(UUID childId, UUID guardianId, FullName fullName) {
		this.childId = childId;
		this.guardianId = guardianId;
		this.fullName = fullName;
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
