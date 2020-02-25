package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.AccountFactory;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

@Service
public class AccountService extends BasicAccountService<AccountRepository, Account> {

	@Autowired
	public AccountService(AccountRepository accountRepository, PasswordManager passwordManager, AccountFactory accountFactory, RoleService roleService) {
		super(accountRepository, passwordManager, accountFactory, roleService);
	}
}
