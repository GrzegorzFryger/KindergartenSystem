package pl.edu.pja.prz.account.facade;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.AccountCredentialDto;
import pl.edu.pja.prz.account.facade.mapper.AccountCredentialMapper;
import pl.edu.pja.prz.account.service.AccountCredentialService;

import java.util.Optional;

@Service
public class AccountCredentialFacade {
	private final AccountCredentialService accountCredentialService;
	private final AccountCredentialMapper accountMapper;

	public AccountCredentialFacade(AccountCredentialService accountCredentialService,AccountCredentialMapper accountMapper) {
		this.accountCredentialService = accountCredentialService;
		this.accountMapper = accountMapper;
	}

	public Optional<AccountCredentialDto> findByEmail(String email) {
		return accountCredentialService.findByEmail(email).map(accountMapper::fromAccount);
	}
}
