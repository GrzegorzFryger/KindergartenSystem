package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService extends AccountService<EmployeeRepository, Employee> {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository accountRepository, PasswordManager passwordManager,
	                       AccountFactory accountFactory, RoleService roleService,
	                       EmployeeRepository employeeRepository) {
		super(accountRepository, passwordManager, accountFactory, roleService);
		this.employeeRepository = employeeRepository;
	}

	public Employee createEmployeeAccount(Person person, String email) {
		return this.createEmployeeAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	public Employee createEmployeeAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, employeeRepository, Employee.class);
	}

	public Employee createAdministratorAccount(Person person, String email) {
		return this.createAdministratorAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	public Employee createAdministratorAccount(Address address, FullName fullName, Phone phone,
	                                           String email) {
		employeeRepository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
		});

		var result = employeeRepository.save(
				accountFactory.createAdministrator(new Person(address, fullName, phone),
						new Password(passwordManager.generateEncodeRandomPassword()),
						email
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

	public Set<Group> getIdGroups(UUID id) {
		return employeeRepository.findById(id)
				.map(Employee::getGroups)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found");
				});
	}
}
