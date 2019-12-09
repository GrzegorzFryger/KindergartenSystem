package pl.edu.pja.tuition.model.value;

import javax.persistence.Embeddable;


@Embeddable
public class FullName {
	private String name;
	private String Surname;

	public FullName() {
	}

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
