package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeFacade {

    EmployeeDto createEmployee(AccountDto accountDto);

    EmployeeDto createAdministratorAccount(AccountDto accountDto);

	EmployeeDto findById(UUID id);

    List<EmployeeDto> findAll();

    Long countEmployee();

    EmployeeDto updateEmployee(EmployeeDto employeeDto);
}
