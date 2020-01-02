package pl.edu.pja.prz.account.facade.mapper;

import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;


public interface EmployeeMapper {
	EmployeeDto fromEmployee(Employee Employee);
	Employee toEmployee(EmployeeDto employeeDto);

	Person toPerson(AccountDto accountDto);
}
