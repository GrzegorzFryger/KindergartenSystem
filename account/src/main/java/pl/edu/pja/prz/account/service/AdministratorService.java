package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.repository.BoroughRepository;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Service
public class AdministratorService {

	private final AdministratorAccountFactoryImpl administratorAccountFactory;
	private final BoroughRepository boroughRepository;
	private final ChildService childService;
	private final EmployeeRepository employeeRepository;
	private final GuardianRepository guardianRepository;
	private final PasswordManager passwordManager;
	private final RoleService roleService;


	@Autowired
	public AdministratorService(AdministratorAccountFactoryImpl administratorAccountFactory,
	                            BoroughRepository boroughRepository, ChildService childService,
	                            EmployeeRepository employeeRepository, GuardianRepository guardianRepository,
	                            PasswordManager passwordManager, RoleService roleService) {
		this.administratorAccountFactory = administratorAccountFactory;
		this.boroughRepository = boroughRepository;
		this.childService = childService;
		this.employeeRepository = employeeRepository;
		this.guardianRepository = guardianRepository;
		this.passwordManager = passwordManager;
		this.roleService = roleService;
	}

	public Guardian createGuardianAccount(Person person, String email) {
		return this.createGuardianAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	public Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, guardianRepository, Guardian.class);
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
				administratorAccountFactory.createAdministrator(new Person(address, fullName, phone),
						new Password(passwordManager.generateEncodeRandomPassword()),
						email
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

	public Child createChild(Long boroughId, Address address, FullName fullName, String pesel,
	                         StudyPeriod studyPeriod) {
		var borough = boroughRepository.findById(boroughId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughId);
		});

		var child = childService.createChild(address, borough, fullName, pesel, studyPeriod);
		addChildToBorough(child, borough);
		return child;

	}

	public Child createChild(Long boroughId, Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		var borough = boroughRepository.findById(boroughId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughId);
		});

		//todo write condition for children without pesel number
		var child = childService.createChild(address, age, borough, fullName, gender, "NOT_SET", studyPeriod);
		addChildToBorough(child, borough);
		return child;

	}

	public void appendChildrenToGuardian(UUID childId, Set<UUID> setGuardianId) {
		var child = childService.getChildById(childId);

		guardianRepository.findAllById(setGuardianId).forEach(guardian -> {
			guardian.addChild(child);
			guardianRepository.save(guardian);
		});
	}

	private void addChildToBorough(Child child, Borough borough) {
		borough.addChild(child);
		boroughRepository.save(borough);
	}

	private <T extends Account> T persistStandardAccount(Address address, FullName fullName, Phone phone,
	                                                     String email, BasicAccountRepository<T> repository,
	                                                     Class<T> tClass) {
		repository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
		});

		var result = repository.save(
				administratorAccountFactory.createStandardAccount(new Person(address, fullName, phone),
						new Password(passwordManager.generateEncodeRandomPassword()),
						email,
						tClass
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

}
