package pl.edu.pja.prz.account.facade.dto;


import java.util.Set;

public class GuardianDto extends AccountDto {
	private Set<ChildDto> children;

	public GuardianDto() {
		super();
	}

	public Set<ChildDto> getChildren() {
		return children;
	}

	public void setChildren(Set<ChildDto> children) {
		this.children = children;
	}
}
