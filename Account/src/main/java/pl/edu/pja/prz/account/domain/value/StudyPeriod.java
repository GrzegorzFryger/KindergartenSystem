package pl.edu.pja.prz.account.domain.value;

import lombok.Value;

import java.time.LocalDate;

@Value
public class StudyPeriod {

	private LocalDate additionDate;
	private LocalDate endingDate;

}
