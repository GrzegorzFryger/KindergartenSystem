package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;


@Embeddable
public class FullName {

	private final String name;
	private final String Surname;

	public FullName(String name, String surname) {
		this.name = name;
		Surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return Surname;
	}
}
