package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;


public abstract class BasicAccountService<T extends BasicAccountRepository<E, ID>, E extends Account, ID>
		extends GenericService<T, E, ID> {
	protected final AccountFactory accountFactory;
	protected final PasswordManager passwordManager;
	protected final RoleService roleService;

	public BasicAccountService(T repository, AccountFactory accountFactory, PasswordManager passwordManager,
	                           RoleService roleService) {
		super(repository);
		this.accountFactory = accountFactory;
		this.passwordManager = passwordManager;
		this.roleService = roleService;
	}

	protected E persistStandardAccount(Address address, FullName fullName, Phone phone,
	                                   String email, Class<E> tClass) {
		repository.findByEmailAndFullName(email, fullName).ifPresent(account -> {
			throw new IllegalArgumentException("Person exist with id: " + account.getId());
		});

		var result = repository.save(
				accountFactory.createStandardAccount(new Person(address, fullName, phone),
						new Password(passwordManager.generateEncodeRandomPassword()),
						email,
						tClass
				)
		);
		roleService.persistRoleFromUser(result);
		return result;
	}
}
