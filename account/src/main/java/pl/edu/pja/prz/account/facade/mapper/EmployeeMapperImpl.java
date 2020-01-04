package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

	@Override
	public EmployeeDto fromEmployee(Employee Employee) {
		return null;
	}

	@Override
	public Employee toEmployee(EmployeeDto employeeDto) {
		return null;
	}

	@Override
	public Person toPerson(AccountDto accountDto) {
		return null;
	}

}
