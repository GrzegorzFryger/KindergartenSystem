package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.PrivilegeType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends BaseEntity<Long> {

	private String description;
	@ElementCollection @Enumerated(EnumType.STRING)
	private Set<PrivilegeType> privileges;
	@ManyToMany @JoinTable(name = "role_account",
			joinColumns = {@JoinColumn(name = "fk_role")},
			inverseJoinColumns = {@JoinColumn(name = "fk_account")})
	private Set<Account> accounts;

	public Role() { }

	public Role(String description, Set<PrivilegeType> privileges) {
		this.description = description;
		this.privileges = privileges;
		this.accounts = new HashSet<>();
		this.privileges = new HashSet<>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<PrivilegeType> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<PrivilegeType> privileges) {
		this.privileges = privileges;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public boolean addPrivilege(PrivilegeType privilege) {
		return this.privileges.add(privilege);
	}

	public boolean removePrivilege(PrivilegeType privilege) {
		return getPrivileges().remove(privilege);
	}

	public boolean hasPrivileges(PrivilegeType... privileges) {
		return (privileges.length > 0) && this.privileges.containsAll(Arrays.asList(privileges));
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;

		Role role = (Role) o;

		if (getDescription() != null ? !getDescription().equals(role.getDescription()) : role.getDescription() != null)
			return false;
		return getPrivileges() != null ? getPrivileges().equals(role.getPrivileges()) : role.getPrivileges() == null;
	}

	@Override public int hashCode() {
		int result = getDescription() != null ? getDescription().hashCode() : 0;
		result = 31 * result + (getPrivileges() != null ? getPrivileges().hashCode() : 0);
		return result;
	}

	@Override public String toString() {
		return "Role{" +
				"description='" + description + '\'' +
				", privileges=" + privileges +
				'}';
	}
}
