package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;

import java.util.Set;
import java.util.UUID;

public interface EmployeeService {
	Employee createEmployeeAccount(Person person, String email);

	Employee createEmployeeAccount(Address address, FullName fullName, Phone phone,
	                               String email);

	Employee createAdministratorAccount(Person person, String email);

	Employee createAdministratorAccount(Address address, FullName fullName, Phone phone,
	                                    String email);

	Set<Group> getIdGroups(UUID id);
}
