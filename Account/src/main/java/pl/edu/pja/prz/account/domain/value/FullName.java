package pl.edu.pja.prz.account.domain.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Embeddable
public class FullName {

	private String name;
	private String Surname;

	public String getFullName() {
		return this.name + " " + this.getSurname();
	}
}
