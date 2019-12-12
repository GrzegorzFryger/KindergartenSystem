package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class Person extends BaseEntity<UUID> {
	private Address address;
	private FullName fullName;
	private Phone phoneNumber;

	Person() {
	}

	public Person(Address address, FullName fullName, Phone phoneNumber) {
		this.address = address;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
	}

	public Phone getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
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
}
