package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends Person  {
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	private Password password;
	@ManyToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
	private String email;

	public Account() {
		super();
	}

	public Account(Address address, FullName fullName, Phone phoneNumber,
	               Password password, String email) {
		super(address, fullName, phoneNumber);
		this.password = password;
		this.email = email;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Account)) return false;
		if (!super.equals(o)) return false;

		Account account = (Account) o;

		if (getAccountStatus() != account.getAccountStatus()) return false;
		if (getPassword() != null ? !getPassword().equals(account.getPassword()) : account.getPassword() != null)
			return false;
		if (getRoles() != null ? !getRoles().equals(account.getRoles()) : account.getRoles() != null) return false;
		return getEmail() != null ? getEmail().equals(account.getEmail()) : account.getEmail() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getAccountStatus() != null ? getAccountStatus().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		return result;
	}
}
