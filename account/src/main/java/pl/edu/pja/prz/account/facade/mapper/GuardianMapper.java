package pl.edu.pja.prz.account.facade.mapper;

import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;


public interface GuardianMapper {
	GuardianDto fromGuardian(Guardian guardian);
	Guardian toGuardian(GuardianDto guardianDto);
	Person toPerson(AccountDto accountDto);
}
