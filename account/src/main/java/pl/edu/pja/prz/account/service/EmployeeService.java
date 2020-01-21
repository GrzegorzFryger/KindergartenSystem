package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Set;
import java.util.UUID;

public interface EmployeeService {
	Employee createEmployeeAccount(Person person, String email);

	Employee createEmployeeAccount(Address address, FullName fullName, Phone phone,
	                               String email);

	Employee createAdministratorAccount(Person person, String email);

	Employee createAdministratorAccount(Address address, FullName fullName, Phone phone,
	                                    String email);

	Employee findById(UUID uuid);

	Set<Group> getIdGroups(UUID id);
}
