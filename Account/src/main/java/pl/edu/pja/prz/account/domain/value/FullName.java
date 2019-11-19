package pl.edu.pja.prz.account.domain.value;

import lombok.Value;

@Value
public class FullName {

	private String name;
	private String Surname;

	public String getFullName() {
		return this.name + " " + this.getSurname();
	}
}
