package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.value.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends Account {

	@ElementCollection(targetClass=String.class)
	private Set<IdentityObject<Long>> groups;
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;

	Employee() { }

	public Employee(Address address, FullName fullName, Phone phoneNumber, Password password, String email,
	                EmployeeType employeeType) {
		super(address, fullName, phoneNumber, password, email);
		this.employeeType = employeeType;
		this.groups = new HashSet<>();
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

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
