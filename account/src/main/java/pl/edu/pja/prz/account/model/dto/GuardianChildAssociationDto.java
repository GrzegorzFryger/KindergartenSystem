package pl.edu.pja.prz.account.model.dto;

import java.util.List;
import java.util.UUID;

public class GuardianChildAssociationDto {
    private List<UUID> children;
    private List<UUID> guardians;

	public GuardianChildAssociationDto() {
	}

    public List<UUID> getChildren() {
        return children;
    }

    public void setChildren(List<UUID> children) {
        this.children = children;
    }

    public List<UUID> getGuardians() {
        return guardians;
    }

    public void setGuardians(List<UUID> guardians) {
        this.guardians = guardians;
	}
}
