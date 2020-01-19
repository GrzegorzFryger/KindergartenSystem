package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends AccountService<EmployeeRepository, Employee> implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordManager passwordManager,
	                           AccountFactory accountFactory, RoleService roleService) {
		super(employeeRepository, passwordManager, accountFactory, roleService);
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createEmployeeAccount(Person person, String email) {
		return this.createEmployeeAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	@Override
	public Employee createEmployeeAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, employeeRepository, Employee.class);
	}

	@Override
	public Employee createAdministratorAccount(Person person, String email) {
		return this.createAdministratorAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	@Override
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

	@Override
	public Set<Group> getIdGroups(UUID id) {
		return employeeRepository.findById(id)
				.map(Employee::getGroups)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found");
				});
	}
}
