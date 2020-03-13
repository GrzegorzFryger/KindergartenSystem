package pl.edu.pja.prz.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.UUID;

@Service
public class AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	private static final String ACCOUNT = "Account";
	private static final String USER = "User";
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
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
