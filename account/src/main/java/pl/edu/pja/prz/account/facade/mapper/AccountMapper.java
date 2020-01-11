package pl.edu.pja.prz.account.facade.mapper;

import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;


public interface AccountMapper {
	AccountDto fromAccount(Account account);

	Account toAccount(AccountDto accountDto);

	Person toPerson(AccountDto accountDto);
}
