package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GuardianServiceImpl extends AccountService<GuardianRepository, Guardian> implements GuardianService {
	private static final String USER = "User";
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
					throw new ElementNotFoundException(USER, id);
				}
		);
	}

	@Override
	public List<Guardian> getAllGuardians() {
		//todo in the future will be add paging
		var a = guardianRepository.findAll().get(0).getChildren();

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


	@Override
	public Optional<Guardian> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street)
			throws IllegalStateException {
		if (street == null) {
			return guardianRepository.findReadOnly((root, query, cb) ->
					cb.equal(root.get(Guardian_.fullName), fullName), Guardian.class)
					.stream()
					.reduce((u, v) -> {
						throw new IllegalStateException("More than one user found");
					});
		} else {
			return guardianRepository.findReadOnly((root, query, cb) ->
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
