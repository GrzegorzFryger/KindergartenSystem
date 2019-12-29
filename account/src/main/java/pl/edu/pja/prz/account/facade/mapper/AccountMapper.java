package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;

@Service
public interface AccountMapper {
	AccountDto fromAccount(Guardian guardian);
	Account toAccount(AccountDto accountDto);
	Person toPerson(AccountDto accountDto);
}
