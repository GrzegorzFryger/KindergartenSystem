package pl.edu.pja.prz.account.model.dto;

import pl.edu.pja.prz.account.model.enums.PrivilegeType;

import java.util.HashSet;
import java.util.Set;

public class RoleDto {
	private String name;
	private Set<PrivilegeType> privileges = new HashSet<>();

	public RoleDto() {
	}

	public RoleDto(String name, Set<PrivilegeType> privileges) {
		this.name = name;
		this.privileges = privileges;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PrivilegeType> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<PrivilegeType> privileges) {
		this.privileges = privileges;
	}
}
