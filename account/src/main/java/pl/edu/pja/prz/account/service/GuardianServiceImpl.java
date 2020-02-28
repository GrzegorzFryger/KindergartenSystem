package pl.edu.pja.prz.account.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GuardianServiceImpl extends BasicAccountService<GuardianRepository,Guardian,UUID>  {
	private static final String USER = "User";
	private final ChildServiceImpl childService;

	public GuardianServiceImpl(GuardianRepository repository, AccountFactory accountFactory, PasswordManager passwordManager,
	                           RoleService roleService, ChildServiceImpl childService) {
		super(repository, accountFactory, passwordManager, roleService);
		this.childService = childService;
	}


	public Guardian createGuardianAccount(Person person, String email) {
		return this.createGuardianAccount(person.getAddress(), person.getFullName(), person.getPhoneNumber(), email);
	}


	public Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                                      String email) {
		return persistStandardAccount(address, fullName, phone, email, Guardian.class);
	}


	public void appendChildrenToGuardian(UUID childId, Set<UUID> setGuardianId) {
		var child = childService.getById(childId);

		repository.findAllById(setGuardianId).forEach(guardian -> {
			guardian.addChild(child);
			repository.save(guardian);
		});
	}


	public Set<Child> getAllChildren(UUID guardianId) {
		return repository.findById(guardianId)
				.map(Guardian::getChildren)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found");
				});
	}

	public Optional<Guardian> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) {
		if (street == null) {
			return repository.findReadOnly((root, query, cb) ->
					cb.equal(root.get(Guardian_.fullName), fullName), Guardian.class)
					.stream()
					.reduce((u, v) -> {
						throw new IllegalStateException("More than one user found");
					});
		} else {
			return repository.findReadOnly((root, query, cb) ->
							cb.and(
									cb.equal(root.get(Guardian_.fullName), fullName),
									cb.like(root.get(Guardian_.address).get(Address_.streetNumber), street)
							)
					, Guardian.class)
					.stream()
					.reduce((u, v) -> {
						throw new IllegalStateException("More than one user found");
					});
		}
	}

}
