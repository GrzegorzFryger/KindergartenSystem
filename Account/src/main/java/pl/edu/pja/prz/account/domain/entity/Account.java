package pl.edu.pja.prz.account.domain.entity;


import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.FullName;
import pl.edu.pja.prz.account.domain.value.Password;


public class Account {

	private Long id;
	private String phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private UserRole userRole;

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
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
}
