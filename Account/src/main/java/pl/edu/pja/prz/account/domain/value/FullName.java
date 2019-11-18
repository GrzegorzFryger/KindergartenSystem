package pl.edu.pja.prz.account.domain.value;

import javax.persistence.Embeddable;

@Embeddable
public class FullName {

	private String name;
	private String Surname;

	public FullName(String name, String surname) {
		this.name = name.toLowerCase();
		Surname = surname.toLowerCase();
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}
}
