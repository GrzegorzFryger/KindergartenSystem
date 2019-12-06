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
	                            BoroughRepository boroughRepository, ChildService childService, EmployeeRepository employeeRepository, GuardianRepository guardianRepository,
	                            PasswordManager passwordManager, RoleService roleService) {
		this.administratorAccountFactory = administratorAccountFactory;
		this.boroughRepository = boroughRepository;
		this.childService = childService;
		this.employeeRepository = employeeRepository;
		this.guardianRepository = guardianRepository;
		this.passwordManager = passwordManager;
		this.roleService = roleService;
	}

	public Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address,fullName,phone,email, guardianRepository, Guardian.class);
	}

	public Employee createEmployeeAccount(Address address, FullName fullName, Phone phone,
	                                       String email) {
		return persistStandardAccount(address,fullName,phone,email, employeeRepository, Employee.class);
	}

	public Employee createAdministratorAccount(Address address, FullName fullName, Phone phone,
	                                            String email) {
		employeeRepository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
		});

		var result = employeeRepository.save(
				administratorAccountFactory.createAdministrator(address,
						fullName,
						phone,
						new Password(passwordManager.generateEncodeRandomPassword()),
						email
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

	public Child createChild(Long boroughId, Address address, FullName fullName,String pesel,
	                         StudyPeriod studyPeriod) {
		var borought = boroughRepository.findById(boroughId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughId);
		});

		var child = childService.createChild(address,borought,fullName ,pesel,studyPeriod);
		addChildToBorough(child,borought);
		return child;

	}

	public Child createChild(Long boroughtId, Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		var borought = boroughRepository.findById(boroughtId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughtId);
		});

		//todo write condition for children without pesel number
		var child = childService.createChild(address,age,borought,fullName ,gender,"NOT_SET",studyPeriod);
		addChildToBorough(child,borought);
		return child;

	}

	public void appendChildrenToGuardian(UUID uuid, Set<UUID> setGuardianId) {
		var child = childService.getChildById(uuid);

		guardianRepository.findAllById(setGuardianId).forEach(guardian -> {
			guardian.addChild(child);
			guardianRepository.save(guardian);
		});
	}

	private void addChildToBorough(Child child, Borough borough) {
		borough.addChil(child);
		boroughRepository.save(borough);
	}

	private void addChildToGuardian(Child child, Borough borough) {
		borough.addChil(child);
		boroughRepository.save(borough);
	}

	private <T extends Account > T persistStandardAccount(Address address, FullName fullName, Phone phone,
	                                                     String email, BasicAccountRepository<T> repository,
	                                                     Class<T> tClass) {
		repository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
		});

		var result = repository.save(
				administratorAccountFactory.createStandardAccount(address,
						fullName,
						phone,
						new Password(passwordManager.generateEncodeRandomPassword()),
						email,
						tClass
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}

}
