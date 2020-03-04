package pl.edu.pja.prz.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.ActivateTokenData;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AccountCredentialService {
	private static final String ACCOUNT = "Account";
	private static final String USER = "User";

	private static final Logger logger = LoggerFactory.getLogger(AccountCredentialService.class);
	private final AccountRepository accountRepository;
	private final ActivateTokenService activateTokenService;
	private final PasswordManager passwordManager;
	private final RoleService roleService;

	public AccountCredentialService(AccountRepository accountRepository, PasswordManager passwordManager,
	                                RoleService roleService, ActivateTokenService activateTokenService) {
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
		this.roleService = roleService;
		this.activateTokenService = activateTokenService;
	}

	public Optional<Account> findByEmail(String email) {
		return accountRepository.findByEmail(email);
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

	public boolean activateAccount(String token, String newPassword, String repeatNewPassword) {

		AtomicBoolean success = new AtomicBoolean(false);

		if(!newPassword.equals(repeatNewPassword)) {
			throw new BusinessException("Password are not equals");
		}

		var authentication = activateTokenService.getAuthentication(token)
				.orElseThrow(() -> new BusinessException("Wrong token"));

		accountRepository
				.findByEmailAndAccountStatus(authentication.getEmail(), AccountStatus.NOT_ACTIVE).map(account -> {

			if (isEquals(authentication, account.getPassword())) {
				var hashPassword = passwordManager.encode(newPassword);
				account.setPassword(new Password(hashPassword));
				return accountRepository.save(account);
			} else {
				logger.warn("Hash password form token not match : {}", authentication.getHasPassword());
				return null;
			}

		}).ifPresentOrElse(account -> {
			//todo sent email if success
			success.set(true);
		},() -> new BusinessException("Can not activate account"));

		return success.get();
	}

	private boolean isEquals(ActivateTokenData token, Password password) {
		return token.getHasPassword().equals(password.getPassword());
	}
}
