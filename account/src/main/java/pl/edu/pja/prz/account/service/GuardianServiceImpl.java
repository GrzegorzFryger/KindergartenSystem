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

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class GuardianServiceImpl extends AccountService<GuardianRepository, Guardian> implements GuardianService {
	private final GuardianRepository guardianRepository;
	private final ChildService childService;

	@Autowired
	public GuardianServiceImpl(GuardianRepository accountRepository, PasswordManager passwordManager,
	                           AccountFactory accountFactory, RoleService roleService,
	                           GuardianRepository guardianRepository, ChildService childService) {
		super(accountRepository, passwordManager, accountFactory, roleService);
		this.guardianRepository = guardianRepository;
		this.childService = childService;
	}

	@Override
	public Guardian createGuardianAccount(Person person, String email) {
		return this.createGuardianAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}

	@Override
	public Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, guardianRepository, Guardian.class);
	}

	@Override
	public Guardian getGuardianById(UUID id) {
		return guardianRepository.findById(id).orElseThrow(() -> {
					throw new IllegalArgumentException("Not found user with id: " + id);
				}
		);
	}

	@Override
	public List<Guardian> getAllGuardians() {
		//todo in the future will be add paging
		return guardianRepository.findAll();
	}

	@Override
	public void appendChildrenToGuardian(UUID childId, Set<UUID> setGuardianId) {
		var child = childService.getChildById(childId);

		guardianRepository.findAllById(setGuardianId).forEach(guardian -> {
			guardian.addChild(child);
			guardianRepository.save(guardian);
		});
	}

	@Override
	public Set<Child> getAllChildren(UUID guardianId) {
		return guardianRepository.findById(guardianId)
				.map(Guardian::getChildren)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found");
				});
	}

}
