package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.PrivilegeType;

import javax.persistence.*;
import java.util.Arrays;
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

	public Role() {
	}

	public Role(String description, Set<PrivilegeType> privileges, Set<Account> accounts) {
		this.description = description;
		this.privileges = privileges;
		this.accounts = accounts;
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

}
