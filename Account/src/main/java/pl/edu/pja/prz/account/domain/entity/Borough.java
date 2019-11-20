package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.Phone;

import javax.persistence.Entity;

@Entity
public class Borough extends BaseEntity<Long> {

	private String name;
	private Address address;
	private Phone phone;
	private String email;
	private String nipNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNipNumber() {
		return nipNumber;
	}

	public void setNipNumber(String nipNumber) {
		this.nipNumber = nipNumber;
	}
}
