package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.UUID;


abstract class AccountService<T extends BasicAccountRepository<E>, E extends Account> {
	private final T accountRepository;
	protected final PasswordManager passwordManager;
	protected final AccountFactory accountFactory;
	protected final RoleService roleService;

	public AccountService(T accountRepository, PasswordManager passwordManager, AccountFactory accountFactory,
	                      RoleService roleService) {
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
		this.accountFactory = accountFactory;
		this.roleService = roleService;
	}

	public boolean signIn(String email, CharSequence rawPassword) {
		return accountRepository.findByEmail(email)
				.map(account -> passwordManager.matches(rawPassword, account.getPassword().getPassword()))
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found account with email " + email);
				});
	}

	public E updatePersonalData(UUID id, Person person) {
		return accountRepository.findById(id).map(
				account -> {
					updateNotEmptyPersonField(account, person);
					return accountRepository.save(account);
				}).orElseThrow(() -> {
			throw new IllegalArgumentException("Not found user with id : ");
		});
	}

	public E updateEmail(UUID id, String email) {
		return accountRepository.findById(id).map(
				account -> {
					//todo send verification email
					account.setEmail(email);
					return accountRepository.save(account);
				}
		).orElseThrow(() -> {
			throw new IllegalArgumentException("Not found user with id : ");
		});
	}

	public Boolean updatePassword(UUID id, CharSequence rawOldPassword, CharSequence rawNewPassword) {
		return accountRepository.findById(id).map(
				account -> {
					//todo send verification email
					if (passwordManager.matches(rawOldPassword, account.getPassword().getPassword())) {
						account.setPassword(new Password(passwordManager.encode(rawNewPassword)));
						return true;
					} else throw new IllegalArgumentException("password not match");
				}
		).orElseThrow(() -> {
			throw new IllegalArgumentException("Not found user with id : ");
		});
	}

	protected void updateNotEmptyPersonField(E account, Person person) {
		if (person.getAddress() != null) {
			account.setAddress(person.getAddress());
		}
		if (person.getFullName() != null) {
			account.setFullName(person.getFullName());
		}
		if (person.getPhoneNumber() != null) {
			account.setPhoneNumber(person.getPhoneNumber());
		}
	}

	protected <T extends Account> T persistStandardAccount(Address address, FullName fullName, Phone phone,
	                                                       String email, BasicAccountRepository<T> repository,
	                                                       Class<T> tClass) {
		repository.findByEmailAndFullName(email, fullName).ifPresent((account) -> {
			throw new IllegalArgumentException("Person exist with email: " + account.getId());
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
