package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Teacher extends Employee {

	public Teacher() {
		super();
	}

	public Teacher(Phone phoneNumber, String email, FullName fullName, Address address, Password password, Set<Role> roles,
	               Set<IdentityObject<Long>> groups) {
		super(phoneNumber, email, fullName, address, password, roles);
		this.groups = groups;
	}

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
