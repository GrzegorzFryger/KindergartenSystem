package pl.edu.pja.prz.account.model.entity;


import pl.edu.pja.prz.account.model.value.Role;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class UserRole extends BaseEntity<Long> {

	@ElementCollection
	private Collection<Role> roles;

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean hasRole(Role... roles) {
		return (roles.length > 0) && this.roles.containsAll(Arrays.asList(roles));
	}
}
