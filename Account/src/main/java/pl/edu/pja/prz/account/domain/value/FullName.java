package pl.edu.pja.prz.account.domain.value;

import javax.persistence.Embeddable;


@Embeddable
public class FullName {

	private String name;
	private String Surname;

	public FullName() {
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return Surname;
	}

	public String getFullName() {
		return this.name + " " + this.name;
	}
}
