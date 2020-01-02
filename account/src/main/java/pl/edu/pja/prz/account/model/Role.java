package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.PrivilegeType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends BaseEntityLong {
	private String name;
	@ElementCollection @Enumerated(EnumType.STRING)
	private Set<PrivilegeType> privileges = new HashSet<>();
	@ManyToMany
	@JoinTable(name = "role_account",
			joinColumns = {@JoinColumn(name = "fk_role")},
			inverseJoinColumns = {@JoinColumn(name = "fk_account")})
	private Set<Account> accounts = new HashSet<>();

	Role() { }

	public Role(String name) {
		this.name = name;
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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account){
		account.getRoles().add(this);
		this.accounts.add(account);
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

		if (getName() != null ? !getName().equals(role.getName()) : role.getName() != null)
			return false;
		return getPrivileges() != null ? getPrivileges().equals(role.getPrivileges()) : role.getPrivileges() == null;
	}

	@Override public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getPrivileges() != null ? getPrivileges().hashCode() : 0);
		return result;
	}

	@Override public String toString() {
		return "Role{" +
				"name='" + name + '\'' +
				", privileges=" + privileges +
				'}';
	}
}
