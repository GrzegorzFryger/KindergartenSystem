package pl.edu.pja.prz.account.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.account.facade.mapper.GuardianMapper;
import pl.edu.pja.prz.account.service.GuardianService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GuardianFacade {
	private final GuardianMapper guardianMapper;
	private final GuardianService guardianService;
	private final ChildMapper childMapper;

	public GuardianFacade(GuardianMapper guardianMapper, GuardianService guardianService,
	                      ChildMapper childMapper) {
		this.guardianMapper = guardianMapper;
		this.guardianService = guardianService;
		this.childMapper = childMapper;
	}

	public GuardianDto createGuardian(AccountDto accountDto) {
		return guardianMapper.fromGuardian(
				guardianService.createGuardianAccount(
						guardianMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	public GuardianDto findGuardianById(UUID id) {
		return guardianMapper.fromGuardian(
				guardianService.getGuardianById(id)
		);
	}

	public List<GuardianDto> findAllGuardians() {
		return guardianService.getAllGuardians()
				.stream()
				.map(guardianMapper::fromGuardian)
				.collect(Collectors.toList());
	}

	public Set<ChildDto> findAllGuardianChildren(UUID id) {
		return guardianService.getAllChildren(id)
				.stream()
				.map(childMapper::fromChild)
				.collect(Collectors.toSet());
	}
}
