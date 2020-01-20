package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;

public interface EmployeeFacade {

    EmployeeDto createEmployee(AccountDto accountDto);

    EmployeeDto createAdministratorAccount(AccountDto accountDto);
}
