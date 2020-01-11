package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Phone;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Borough extends BaseEntityLong {
	private String name;
	private Address address;
	private Phone phone;
	private String email;
	private String nipNumber;
	@OneToMany(mappedBy = "borough")
	private Set<Child> children = new HashSet<>();

	public Borough() {
	}

	public Borough(String name, Address address, Phone phone, String email, String nipNumber, Set<Child> children) {
		this(name, address, phone, email, nipNumber);
		this.children = children;
	}

	public Borough(String name, Address address, Phone phone, String email, String nipNumber) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.nipNumber = nipNumber;

	}

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

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public boolean addChild(Child child) {
		child.setBorough(this);
		return children.add(child);
	}

	public boolean removeChild(Child child) {
		child.setBorough(null);
		return children.remove(child);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Borough)) return false;
		if (!super.equals(o)) return false;

		Borough borough = (Borough) o;

		if (getName() != null ? !getName().equals(borough.getName()) : borough.getName() != null) return false;
		if (getAddress() != null ? !getAddress().equals(borough.getAddress()) : borough.getAddress() != null)
			return false;
		if (getPhone() != null ? !getPhone().equals(borough.getPhone()) : borough.getPhone() != null) return false;
		if (getEmail() != null ? !getEmail().equals(borough.getEmail()) : borough.getEmail() != null) return false;
		if (getNipNumber() != null ? !getNipNumber().equals(borough.getNipNumber()) : borough.getNipNumber() != null)
			return false;
		return getChildren() != null ? getChildren().equals(borough.getChildren()) : borough.getChildren() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
		result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getNipNumber() != null ? getNipNumber().hashCode() : 0);
		result = 31 * result + (getChildren() != null ? getChildren().hashCode() : 0);
		return result;
	}
}
