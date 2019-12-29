package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.facade.mapper.AccountMapper;
import pl.edu.pja.prz.account.facade.mapper.EmployeeMapper;
import pl.edu.pja.prz.account.facade.mapper.GuardianMapper;
import pl.edu.pja.prz.account.service.AdministratorService;

@Service
public class AdministratorAccountFacade {
	private final AdministratorService administratorService;
	private final EmployeeMapper employeeMapper;
	private final GuardianMapper guardianMapper;
	private final AccountMapper accountMapper;

	@Autowired
	public AdministratorAccountFacade(AdministratorService administratorService, EmployeeMapper employeeMapper,
	                                  GuardianMapper guardianMapper, AccountMapper accountMapper) {
		this.administratorService = administratorService;
		this.employeeMapper = employeeMapper;
		this.guardianMapper = guardianMapper;
		this.accountMapper = accountMapper;
	}

	public GuardianDto createGuardian(AccountDto accountDto) {
		return guardianMapper.fromGuardian(
				administratorService.createGuardianAccount(
						accountMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	public EmployeeDto createEmployee(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				administratorService.createEmployeeAccount(
						accountMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	private EmployeeDto createAdministratorAccount(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				administratorService.createAdministratorAccount(
						accountMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}





}
