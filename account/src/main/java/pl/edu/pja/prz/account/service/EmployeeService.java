package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.value.IdentityObject;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Component
public class EmployeeService extends AccountServiceImpl<EmployeeRepository, Employee>
		implements AccountService<EmployeeRepository,Employee> {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(PasswordManager passwordManager, EmployeeRepository employeeRepository) {
		super(employeeRepository, passwordManager);
		this.employeeRepository = employeeRepository;
	}

	public Set<IdentityObject<Long>> getIdGrups(UUID id) {
		return employeeRepository.findById(id)
				.map(Employee::getGroups)
				.orElseThrow(()-> {throw new IllegalArgumentException("Not found");});
	}
}
