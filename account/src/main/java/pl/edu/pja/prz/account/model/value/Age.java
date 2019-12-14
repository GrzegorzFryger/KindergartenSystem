package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Age {
	private LocalDate dateOfBirth;

	public Age() {
	}

	public Age(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Age)) return false;

		Age age = (Age) o;

		return getDateOfBirth() != null ? getDateOfBirth().equals(age.getDateOfBirth()) : age.getDateOfBirth() == null;
	}

	@Override
	public int hashCode() {
		return getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Age{" +
				"dateOfBirth=" + dateOfBirth +
				'}';
	}
}
