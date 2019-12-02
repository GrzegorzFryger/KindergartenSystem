package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.value.IdentityObject;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Employee extends Account {


	private EmployeeType employeeType;
	@ElementCollection(targetClass=String.class)
	private Set<IdentityObject<Long>> groups;

	public Set<IdentityObject<Long>> getGroups() {
		return groups;
	}

	public void setGroups(Set<IdentityObject<Long>> groups) {
		this.groups = groups;
	}

	public boolean addGrup(IdentityObject<Long> group) {
		return groups.add(group);
	}

	public boolean removeGrup(IdentityObject<Long> group) {
		return groups.remove(group);
	}


}
