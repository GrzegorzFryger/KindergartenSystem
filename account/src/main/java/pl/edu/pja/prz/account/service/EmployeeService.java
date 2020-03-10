package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService extends BasicAccountService<EmployeeRepository, Employee> {

	public EmployeeService(EmployeeRepository repository, AccountFactory accountFactory, PasswordManager passwordManager,
	                       RoleService roleService) {
		super(repository, accountFactory, passwordManager, roleService);

	}

	public Employee createEmployeeAccount(Person person, String email) {
		return persistStandardAccount(
				person.getAddress(),
				person.getFullName(),
				person.getPhoneNumber(),
				email,
				Employee.class
		);
	}

	public Employee createAdministratorAccount(Person person, String email) {
		return this.createAdministratorAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	public Employee createAdministratorAccount(Address address, FullName fullName, Phone phone,
	                                           String email) {
		repository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
		});

		var result = repository.save(
				accountFactory.createAdministrator(new Person(address, fullName, phone),
						new Password(passwordManager.generateEncodeRandomPassword()),
						email
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

	public Set<Group> getIdGroups(UUID id) {
		return repository.findById(id)
				.map(Employee::getGroups)
				.orElseThrow(() -> {
					throw new ElementNotFoundException("Employee", "Not found with id" + id);
				});
	}
}
