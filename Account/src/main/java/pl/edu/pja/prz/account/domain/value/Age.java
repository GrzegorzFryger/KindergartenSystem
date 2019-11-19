package pl.edu.pja.prz.account.domain.value;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Age {

	LocalDate dateOfBirth;

	public Integer getAge() {
		return LocalDate.now().getYear() - dateOfBirth.getYear();
	}
}
