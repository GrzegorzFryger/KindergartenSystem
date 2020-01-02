package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;

@Component
public class GuardianMapperImpl implements GuardianMapper {

	private final AccountMapper accountMapper;

	@Autowired
	public GuardianMapperImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public GuardianDto fromGuardian(Guardian guardian) {
		if (guardian == null) {
			return null;
		}

		var guardianDto = new GuardianDto();
		guardianDto.setAccountDto(accountMapper.fromAccount(guardian));
		guardianDto.setId(guardian.getId());

		return guardianDto;
	}

	@Override
	public Guardian toGuardian(GuardianDto guardianDto) {
		if (guardianDto == null) {
			return null;
		}

		var guardian = new Guardian();
		fromAccountDto(guardian, guardianDto.getAccountDto());

		return guardian;
	}

	@Override
	public Person toPerson(AccountDto accountDto) {
		if (accountDto == null) {
			return null;
		}

		return accountMapper.toPerson(accountDto);
	}

	private void fromAccountDto(Guardian guardian, AccountDto accountDto) {
		var account = accountMapper.toAccount(accountDto);

		guardian.setAddress(account.getAddress());
		guardian.setFullName(account.getFullName());
		guardian.setEmail(account.getEmail());
		guardian.setAccountStatus(account.getAccountStatus());
	}
}
