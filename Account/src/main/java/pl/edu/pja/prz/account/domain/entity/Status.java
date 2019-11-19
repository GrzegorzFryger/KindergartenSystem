package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.ChildrenStatus;

import java.util.Arrays;
import java.util.List;

public class Status extends BaseEntity<Long> {

	private List<ChildrenStatus> childrenStatuses;

	public List<ChildrenStatus> getChildrenStatuses() {
		return childrenStatuses;
	}

	public void setChildrenStatuses(List<ChildrenStatus> childrenStatuses) {
		this.childrenStatuses = childrenStatuses;
	}

	public boolean hasStatus(ChildrenStatus... status) {
		return (status.length > 1) && childrenStatuses.containsAll(Arrays.asList(status));
	}
}
