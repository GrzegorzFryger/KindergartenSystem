package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.mapper.AccountMapper;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.service.AccountService;

@Component
public class AccountFacadeImpl implements AccountFacade {
	private final AccountService accountService;
	private final AccountMapper accountMapper;

	@Autowired
	public AccountFacadeImpl(AccountService accountService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.accountMapper = accountMapper;
	}

	@Override
	public AccountDto findAccountByEmail(String email){
		return accountMapper.fromAccount(
				accountService.findByEmail(email)
		);
	}

	@Override
    public AccountDto updatePersonalData(AccountDto accountDto) {
        return accountMapper.fromAccount(
                accountService.updatePersonalData(
                        this.accountMapper.toAccount(accountDto)
                )
        );
	}


}
