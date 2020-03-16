package pl.edu.pja.prz.account.facade;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.dto.AccountActivateDto;
import pl.edu.pja.prz.account.model.dto.AccountCredentialDto;
import pl.edu.pja.prz.account.mapper.AccountCredentialMapper;
import pl.edu.pja.prz.account.service.AccountCredentialService;

import java.util.Optional;

@Service
public class AccountCredentialFacadeImpl implements AccountCredentialFacade {
	private final AccountCredentialService accountCredentialService;
	private final AccountCredentialMapper accountMapper;

	public AccountCredentialFacadeImpl(AccountCredentialService accountCredentialService, AccountCredentialMapper accountMapper) {
		this.accountCredentialService = accountCredentialService;
		this.accountMapper = accountMapper;
	}

	@Override
	public Optional<AccountCredentialDto> findByEmail(String email) {
		return accountCredentialService.findByEmail(email).map(accountMapper::fromAccount);
	}

	@Override
	public boolean activateAccount(AccountActivateDto accountActivateDto) {
		return accountCredentialService.activateAccount(
				accountActivateDto.getToken(),
				accountActivateDto.getRawPassword(),
				accountActivateDto.getRepeatRawPassword()
		);

	}
}
