package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.mapper.AccountMapper;
import pl.edu.pja.prz.account.service.AccountService;

@Component
public class AccountFacade {
	private final AccountService accountService;
	private final AccountMapper accountMapper;

	@Autowired
	public AccountFacade(AccountService accountService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.accountMapper = accountMapper;
	}

	public AccountDto findAccountByEmail(String email){
		return accountMapper.fromAccount(
				accountService.findByEmail(email)
		);
	}

}
