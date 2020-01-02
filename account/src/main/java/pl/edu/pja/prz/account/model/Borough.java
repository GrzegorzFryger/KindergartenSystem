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

	Borough() {
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
}
