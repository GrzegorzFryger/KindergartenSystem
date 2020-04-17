package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.mapper.EmployeeMapper;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;
import pl.edu.pja.prz.account.service.EmployeeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {
	private final EmployeeMapper employeeMapper;
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeFacadeImpl(EmployeeMapper employeeMapper, EmployeeService employeeService) {
		this.employeeMapper = employeeMapper;
		this.employeeService = employeeService;
	}

	@Override
	public EmployeeDto createEmployee(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				employeeService.createEmployeeAccount(
						employeeMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	@Override
	public EmployeeDto createAdministratorAccount(AccountDto accountDto) {
		return employeeMapper.fromEmployee(
				employeeService.createAdministratorAccount(
						employeeMapper.toPerson(accountDto),
						accountDto.getEmail()
				)
		);
	}

	@Override
	public EmployeeDto findById(UUID id) {
		return employeeMapper.fromEmployee(employeeService
						.getById(id));
	}

	@Override
	public List<EmployeeDto> findAll() {
		return employeeService.getAll()
				.stream()
				.map(employeeMapper::fromEmployee)
				.collect(Collectors.toList());
	}

	@Override
	public Long countEmployee() {
		return this.employeeService.count();
	}


	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		return this.employeeMapper.fromEmployee(
				this.employeeService.update(this.employeeMapper.toEmployee(employeeDto))
		);
	}

}
