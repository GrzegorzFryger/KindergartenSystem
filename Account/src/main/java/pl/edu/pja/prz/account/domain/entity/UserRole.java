package pl.edu.pja.prz.account.domain.entity;


import pl.edu.pja.prz.account.domain.value.Role;

import java.util.Arrays;
import java.util.List;


public class UserRole {

	private Long id;
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean hasRole(Role... roles) {
		return (roles.length > 0) && this.roles.containsAll(Arrays.asList(roles));
	}
}
