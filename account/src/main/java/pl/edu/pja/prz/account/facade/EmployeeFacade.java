package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;

import java.util.UUID;

public interface EmployeeFacade {

    EmployeeDto createEmployee(AccountDto accountDto);

    EmployeeDto createAdministratorAccount(AccountDto accountDto);

	EmployeeDto findById(UUID id);
}
