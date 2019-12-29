package pl.edu.pja.prz.account.facade.dto;

import pl.edu.pja.prz.account.model.enums.AccountStatus;

public class AccountDto extends PersonDto {
	private AccountStatus status;
	private String email;

	public AccountDto() {
		super();
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
