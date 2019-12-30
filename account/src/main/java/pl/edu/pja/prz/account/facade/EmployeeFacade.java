package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;
import pl.edu.pja.prz.account.facade.mapper.AccountMapper;
import pl.edu.pja.prz.account.facade.mapper.EmployeeMapper;
import pl.edu.pja.prz.account.service.EmployeeService;

@Service
public class EmployeeFacade {
	private EmployeeMapper employeeMapper;
	private EmployeeService employeeService;

	@Autowired
	public EmployeeFacade(EmployeeMapper employeeMapper, EmployeeService employeeService, AccountMapper accountMapper) {
		this.employeeMapper = employeeMapper;
		this.employeeService = employeeService;
	}

	public EmployeeDto createEmployee(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				employeeService.createEmployeeAccount(
						employeeMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	private EmployeeDto createAdministratorAccount(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				employeeService.createAdministratorAccount(
						employeeMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

}
