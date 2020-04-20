package pl.edu.pja.prz.account.model.dto;

import java.util.List;
import java.util.UUID;

public class GuardianChildAssociationDto {
	private List<UUID> childId;
	private List<UUID> guardianId;

	public GuardianChildAssociationDto() {
	}

	public List<UUID> getChildId() {
		return childId;
	}

	public void setChildId(List<UUID> childId) {
		this.childId = childId;
	}

	public List<UUID> getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(List<UUID> guardianId) {
		this.guardianId = guardianId;
	}
}
