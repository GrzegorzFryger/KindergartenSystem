package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.commons.model.BaseEntityUuid;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntityUuid {
	private Address address;
	private FullName fullName;
	private Phone phoneNumber;

	public Person() {
		super();
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		if (!super.equals(o)) return false;

		Person person = (Person) o;

		if (getAddress() != null ? !getAddress().equals(person.getAddress()) : person.getAddress() != null)
			return false;
		if (getFullName() != null ? !getFullName().equals(person.getFullName()) : person.getFullName() != null)
			return false;
		return getPhoneNumber() != null ? getPhoneNumber().equals(person.getPhoneNumber()) : person.getPhoneNumber() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
		result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
		result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Person{" +
				"address=" + address +
				", fullName=" + fullName +
				", phoneNumber=" + phoneNumber +
				'}';
	}
}
