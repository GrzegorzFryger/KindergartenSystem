package pl.edu.pja.prz.commons.model;

import java.util.UUID;

public class GuardianChildDependency {
	private UUID guardianId;
	private UUID childId;
	private FullName childFullName;

	public GuardianChildDependency() {
	}

	public GuardianChildDependency(UUID guardianId, UUID childId, FullName childFullName) {
		this.guardianId = guardianId;
		this.childId = childId;
		this.childFullName = childFullName;
	}

	public UUID getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(UUID guardianId) {
		this.guardianId = guardianId;
	}

	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public FullName getChildFullName() {
		return childFullName;
	}

	public void setChildFullName(FullName childFullName) {
		this.childFullName = childFullName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GuardianChildDependency)) return false;

		GuardianChildDependency that = (GuardianChildDependency) o;

		if (getGuardianId() != null ? !getGuardianId().equals(that.getGuardianId()) : that.getGuardianId() != null)
			return false;
		if (getChildId() != null ? !getChildId().equals(that.getChildId()) : that.getChildId() != null) return false;
		return getChildFullName() != null ? getChildFullName().equals(that.getChildFullName()) : that.getChildFullName() == null;
	}

	@Override
	public int hashCode() {
		int result = getGuardianId() != null ? getGuardianId().hashCode() : 0;
		result = 31 * result + (getChildId() != null ? getChildId().hashCode() : 0);
		result = 31 * result + (getChildFullName() != null ? getChildFullName().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "GuardianChildDependency{" +
				"guardianId=" + guardianId +
				", childId=" + childId +
				", childFullName=" + childFullName +
				'}';
	}
}
