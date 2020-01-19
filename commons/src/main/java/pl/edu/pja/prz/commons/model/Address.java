package pl.edu.pja.prz.commons.model;

import javax.persistence.Embeddable;


@Embeddable
public class Address {
	private String postalCode;
	private String city;
	private String streetNumber;

	public Address() {
	}

	public Address(String postalCode, String city, String streetNumber) {
		this.postalCode = postalCode;
		this.city = city;
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;

		Address address = (Address) o;

		if (getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null)
			return false;
		if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
		return getStreetNumber() != null ? getStreetNumber().equals(address.getStreetNumber()) : address.getStreetNumber() == null;
	}

	@Override
	public int hashCode() {
		int result = getPostalCode() != null ? getPostalCode().hashCode() : 0;
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		result = 31 * result + (getStreetNumber() != null ? getStreetNumber().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Address{" +
				"postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", streetNumber='" + streetNumber + '\'' +
				'}';
	}



}
