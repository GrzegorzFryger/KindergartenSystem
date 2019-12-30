package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Component
public class GuardianService extends AccountService<GuardianRepository, Guardian> {
	private final GuardianRepository guardianRepository;
	private final ChildService childService;

	@Autowired
	public GuardianService(GuardianRepository accountRepository, PasswordManager passwordManager,
	                       AccountFactory accountFactory, RoleService roleService,
	                       GuardianRepository guardianRepository, ChildService childService) {
		super(accountRepository, passwordManager, accountFactory, roleService);
		this.guardianRepository = guardianRepository;
		this.childService = childService;
	}

	public Guardian createGuardianAccount(Person person, String email) {
		return this.createGuardianAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	public Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, guardianRepository, Guardian.class);
	}

	public void appendChildrenToGuardian(UUID childId, Set<UUID> setGuardianId) {
		var child = childService.getChildById(childId);

		guardianRepository.findAllById(setGuardianId).forEach(guardian -> {
			guardian.addChild(child);
			guardianRepository.save(guardian);
		});
	}

	public Set<Child> getAllChildren(UUID id) {
		return guardianRepository.findById(id)
				.map(Guardian::getChildren)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found");
				});
	}

}
