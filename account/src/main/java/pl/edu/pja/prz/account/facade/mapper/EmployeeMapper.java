package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;
import pl.edu.pja.prz.account.model.Employee;

@Service
public interface EmployeeMapper extends AccountMapper {
	EmployeeDto fromEmployee(Employee Employee);

	Employee toEmployee(EmployeeDto employeeDto);
}
