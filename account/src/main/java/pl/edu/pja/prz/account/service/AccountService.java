package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.UUID;

@Service
public class AccountService{
	private static final String ACCOUNT = "Account";
	private static final String USER = "User";
	private final AccountRepository accountRepository;
	protected final PasswordManager passwordManager;
	protected final AccountFactory accountFactory;
	protected final RoleService roleService;

	public AccountService(AccountRepository accountRepository, PasswordManager passwordManager,
	                      AccountFactory accountFactory, RoleService roleService) {
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
		this.accountFactory = accountFactory;
		this.roleService = roleService;
	}

	public Account findById(UUID uuid) {
		return accountRepository.findById(uuid).orElseThrow(() -> new ElementNotFoundException(uuid));
	}

	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException(email));
	}

	public Account updatePersonalData(Person person) {
		return accountRepository.findById(person.getId()).map(
				account -> {
					updateNotEmptyPersonField(account, person);
					return accountRepository.save(account);
				}).orElseThrow(() -> {
			throw new ElementNotFoundException(USER, person.getId());
		});
	}

	protected void updateNotEmptyPersonField(Person oldPerson, Person newPerson) {
		if (newPerson.getAddress() != null) {
			oldPerson.setAddress(newPerson.getAddress());

		}
		if (newPerson.getFullName() != null) {
			oldPerson.setFullName(newPerson.getFullName());
		}
		if (newPerson.getPhoneNumber() != null) {
			oldPerson.setPhoneNumber(newPerson.getPhoneNumber());
		}
	}
}
