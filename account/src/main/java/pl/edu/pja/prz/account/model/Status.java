package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.ChildStatus;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Status extends BaseEntity<Long> {

	@ElementCollection
	private Collection<ChildStatus> childStatuses;

	public Collection<ChildStatus> getChildStatuses() {
		return childStatuses;
	}

	public void setChildStatuses(Collection<ChildStatus> childStatuses) {
		this.childStatuses = childStatuses;
	}

	public boolean hasStatus(ChildStatus... status) {
		return (status.length > 1) && childStatuses.containsAll(Arrays.asList(status));
	}
}
