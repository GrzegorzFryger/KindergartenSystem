package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.facade.dto.AccountDto;

public interface AccountFacade {
	AccountDto findAccountByEmail(String email);
}
