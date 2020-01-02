package pl.edu.pja.prz.account.facade.dto;


import java.util.Set;
import java.util.UUID;

public class GuardianDto {
	private UUID id;
	private AccountDto accountDto;
	private Set<ChildDto> children;

	public GuardianDto() {
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

	public Set<ChildDto> getChildren() {
		return children;
	}

	public void setChildren(Set<ChildDto> children) {
		this.children = children;
	}
}
