package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Age {

	LocalDate dateOfBirth;

	public Age() {
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return LocalDate.now().getYear() - dateOfBirth.getYear();
	}
}
