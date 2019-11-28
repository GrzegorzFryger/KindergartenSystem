package pl.edu.pja.prz.account.model.entity;

import pl.edu.pja.prz.account.model.value.ChildrenStatus;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Status extends BaseEntity<Long> {

	@ElementCollection
	private Collection<ChildrenStatus> childrenStatuses;

	public Collection<ChildrenStatus> getChildrenStatuses() {
		return childrenStatuses;
	}

	public void setChildrenStatuses(Collection<ChildrenStatus> childrenStatuses) {
		this.childrenStatuses = childrenStatuses;
	}

	public boolean hasStatus(ChildrenStatus... status) {
		return (status.length > 1) && childrenStatuses.containsAll(Arrays.asList(status));
	}
}
