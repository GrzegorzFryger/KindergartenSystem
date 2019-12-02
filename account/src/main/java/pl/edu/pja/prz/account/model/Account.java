package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account extends Person  {

	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private Password password;
	@ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
	private Set<Role> roles;
	private String email;

	 Account() { }

	public Account(Address address, FullName fullName, Phone phoneNumber,
	               Password password, String email) {
		super(address, fullName, phoneNumber);
		this.password = password;
		this.email = email;
		this.roles = new HashSet<>();
	}

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
