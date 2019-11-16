package pl.edu.pja.prz.account.domain.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String postalCode;
	private String city;
	private String streetNumber;

	public Address() {
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

}
