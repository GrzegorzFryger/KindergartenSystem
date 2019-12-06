package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.UUID;


abstract class AccountServiceImpl<T extends BasicAccountRepository<E>, E extends Account> implements AccountService<T, E> {
	private final T  accountRepository;
	private final PasswordManager passwordManager;

	public AccountServiceImpl(T accountRepository, PasswordManager passwordManager) {
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
	}

	@Override public boolean signIn(String email, CharSequence rawPassword) {
		return accountRepository.findByEmail(email)
				.map(account -> passwordManager.matches(rawPassword, account.getPassword().getPassword()))
				.orElseThrow(() -> {
					throw new IllegalArgumentException("Not found account with email " + email);
				});
	}

	@Override public E updatePersonalData(UUID id, Person person){
		return 	accountRepository.findById(id).map(
				account -> {
					updateNotEmptyPersonField(account,person);
					return accountRepository.save(account);
				}).orElseThrow(() -> {
					throw new IllegalArgumentException("Not found user with id : " );
				});
	}

	@Override public E updateEmail(UUID id, String email) {
		return accountRepository.findById(id).map(
				account -> {
					//todo send verification email
					account.setEmail(email);
					return accountRepository.save(account);
				}
		).orElseThrow(() -> {
			throw new IllegalArgumentException("Not found user with id : " );
		});
	}

	@Override public Boolean updatePassword(UUID id, CharSequence rawOldPassword, CharSequence rawNewPassword) {
		return accountRepository.findById(id).map(
				account -> {
					//todo send verification email
					if(passwordManager.matches(rawOldPassword,account.getPassword().getPassword())) {
						account.setPassword(new Password(passwordManager.encode(rawNewPassword)));
						return true;
					} else throw new IllegalArgumentException("password not match");
				}
		).orElseThrow(() -> {
			throw new IllegalArgumentException("Not found user with id : " );
		});
	}

	private void updateNotEmptyPersonField(E account, Person person) {
		if(person.getAddress()!= null){
			account.setAddress(person.getAddress());
		}
		if(person.getFullName()!= null){
			account.setFullName(person.getFullName());
		}
		if(person.getPhoneNumber()!= null){
			account.setPhoneNumber(person.getPhoneNumber());
		}
	}


}
