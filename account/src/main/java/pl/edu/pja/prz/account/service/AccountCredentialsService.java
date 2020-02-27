package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.UUID;

@Service
public class AccountCredentialsService {
	private static final String ACCOUNT = "Account";
	private static final String USER = "User";
	private final AccountRepository accountRepository;
	protected final PasswordManager passwordManager;
	protected final RoleService roleService;

	public AccountCredentialsService(AccountRepository accountRepository, PasswordManager passwordManager, RoleService roleService) {
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
		this.roleService = roleService;
	}

	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException(ACCOUNT, email));
	}


	public Account updateEmail(UUID id, String email) {
		return accountRepository.findById(id).map(
				account -> {
					//todo send verification email
					account.setEmail(email);
					return accountRepository.save(account);
				}
		).orElseThrow(() -> {
			throw new ElementNotFoundException(USER, id);
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
			throw new ElementNotFoundException(USER, id);
		});
	}
}
