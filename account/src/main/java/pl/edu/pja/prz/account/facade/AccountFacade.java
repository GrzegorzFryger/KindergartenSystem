package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.model.dto.AccountDto;

public interface AccountFacade {
	AccountDto findAccountByEmail(String email);
}
