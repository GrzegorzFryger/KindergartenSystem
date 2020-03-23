package pl.edu.pja.prz.account.model.dto;

import java.util.UUID;

public class GuardianChildAssociationDto {
	private UUID childId;
	private UUID guardianId;

	public GuardianChildAssociationDto() {
	}

	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public UUID getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(UUID guardianId) {
		this.guardianId = guardianId;
	}
}
