package pl.edu.pja.prz.account.model.dto;

import pl.edu.pja.prz.account.model.enums.AccountStatus;

import java.util.Set;
import java.util.UUID;

public class AccountCredentialDto {
	private UUID id;
	private String email;
	private String password;
	private Set<RoleDto> roles;
	private AccountStatus status;

	public AccountCredentialDto() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
}
