package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee extends Account {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "employee_id")
	private Set<Group> groups;
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;

	Employee() {
	}

	public Employee(Address address, FullName fullName, Phone phoneNumber, Password password, String email,
	                EmployeeType employeeType) {
		super(address, fullName, phoneNumber, password, email);
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public boolean addGroup(Group group) {
		return groups.add(group);
	}

	public boolean removeGrup(Group group) {
		return groups.remove(group);
	}


}
