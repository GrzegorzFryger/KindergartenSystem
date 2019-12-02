package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

abstract class AccountAbstractFactory {
	Employee creteEmployee(Address address, FullName fullName,
	                       Phone phone, Password password, String email, EmployeeType employeeType) {
		return new Employee(address,fullName,phone,password,email, employeeType);
	}
}
