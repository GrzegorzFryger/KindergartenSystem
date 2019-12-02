package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Password;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account extends Person  {

	private String email;
	private Password password;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	@ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
	private Set<Role> roles;

	Account() { }


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean addRole(Role role) {
		role.getAccounts().add(this);
		return this.roles.add(role);
	}

	public boolean removeRole(Role role) {
		role.getAccounts().add(this);
		return this.roles.add(role);
	}



}
