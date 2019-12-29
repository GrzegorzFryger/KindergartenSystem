package pl.edu.pja.prz.account.facade.dto;

import pl.edu.pja.prz.account.model.enums.EmployeeType;

import java.util.UUID;

public class EmployeeDto {
	private UUID id;
	private AccountDto accountDto;
	private EmployeeType employeeType;

	public EmployeeDto() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
}
