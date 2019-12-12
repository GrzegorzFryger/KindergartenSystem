package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;

import java.util.UUID;

public interface AccountService<T extends BasicAccountRepository<E>, E extends Account> {
	boolean signIn(String email, CharSequence rawPassword);

	E updatePersonalData(UUID id, Person person);

	E updateEmail(UUID id, String email);

	Boolean updatePassword(UUID id, CharSequence rawOldPassword, CharSequence rawNewPassword);
}
