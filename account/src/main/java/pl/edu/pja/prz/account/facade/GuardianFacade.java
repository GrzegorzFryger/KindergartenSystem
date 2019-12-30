package pl.edu.pja.prz.account.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.facade.mapper.GuardianMapper;
import pl.edu.pja.prz.account.service.GuardianService;

@Component
public class GuardianFacade {
	private final GuardianMapper guardianMapper;
	private final GuardianService guardianService;

	public GuardianFacade(GuardianMapper guardianMapper, GuardianService guardianService) {
		this.guardianMapper = guardianMapper;
		this.guardianService = guardianService;
	}

	public GuardianDto createGuardian(AccountDto accountDto) {
		return guardianMapper.fromGuardian(
				guardianService.createGuardianAccount(
						guardianMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}
}
