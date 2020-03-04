package pl.edu.pja.prz.account.service;

import org.jooq.lambda.Unchecked;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GuardianService extends BasicAccountService<GuardianRepository,Guardian>  {
	private static final String USER = "User";
	private final ChildService childService;

	public GuardianService(GuardianRepository repository, AccountFactory accountFactory, PasswordManager passwordManager,
	                       RoleService roleService, ChildService childService) {
		super(repository, accountFactory, passwordManager, roleService);
		this.childService = childService;
	}

	public Guardian createGuardianAccount(Person person, String email) {
		return persistStandardAccount(
				person.getAddress(),
				person.getFullName(),
				person.getPhoneNumber(),
				email,
				Guardian.class
		);
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

	/**
	 *
	 * @param fullName
	 * @param street  optional param
	 * @return Optional<Guardian> return guardian when find only one user, otherwise null
	 * @throws Exception - when find more then one Guardian
	 */

	public Optional<Guardian> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) throws MoreThanOneElement {
		if (street == null) {
			return repository.findReadOnly((root, query, cb) ->
					cb.equal(root.get(Guardian_.fullName), fullName), Guardian.class)
					.stream()
					.reduce((u, v) -> {
						Unchecked.throwChecked(new MoreThanOneElement(fullName));
						return null;
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
						Unchecked.throwChecked(new MoreThanOneElement(fullName));
						return null;
					});
		}
	}





}
