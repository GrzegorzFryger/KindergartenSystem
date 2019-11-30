package pl.edu.pja.prz.account.model;


import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

@Entity
public abstract class Account extends BaseEntity<UUID> {

	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;

	@ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
	private Set<Role> roles;

	protected Account() {
	}


	public Phone getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
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
