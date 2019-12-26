package pl.edu.pja.prz.account.facade.dto;

import java.util.UUID;

public class PersonDto {
	private UUID id;
	private String name;
	private String surname;
	private String postalCode;
	private String city;
	private String streetNumber;
	private String phone;

	public PersonDto() {
	}

	public PersonDto(UUID id, String name, String surname, String postalCode, String city, String streetNumber,
	                 String phone) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.postalCode = postalCode;
		this.city = city;
		this.streetNumber = streetNumber;
		this.phone = phone;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PersonDto)) return false;

		PersonDto personDto = (PersonDto) o;

		if (getId() != null ? !getId().equals(personDto.getId()) : personDto.getId() != null) return false;
		if (getName() != null ? !getName().equals(personDto.getName()) : personDto.getName() != null) return false;
		if (getSurname() != null ? !getSurname().equals(personDto.getSurname()) : personDto.getSurname() != null)
			return false;
		if (getPostalCode() != null ? !getPostalCode().equals(personDto.getPostalCode()) : personDto.getPostalCode() != null)
			return false;
		if (getCity() != null ? !getCity().equals(personDto.getCity()) : personDto.getCity() != null) return false;
		if (getStreetNumber() != null ? !getStreetNumber().equals(personDto.getStreetNumber()) : personDto.getStreetNumber() != null)
			return false;
		return getPhone() != null ? getPhone().equals(personDto.getPhone()) : personDto.getPhone() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
		result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		result = 31 * result + (getStreetNumber() != null ? getStreetNumber().hashCode() : 0);
		result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PersonDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", streetNumber='" + streetNumber + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
