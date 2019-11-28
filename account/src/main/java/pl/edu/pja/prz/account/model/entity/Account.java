package pl.edu.pja.prz.account.model.entity;


import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Account extends BaseEntity<UUID> {


	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	@ManyToOne
	private UserRole userRole;

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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Account() {
	}

}
