package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.dto.AccountDto;

public interface AccountFacade {
	AccountDto findAccountByEmail(String email);
	Account updatePersonalData(Person person);
}
