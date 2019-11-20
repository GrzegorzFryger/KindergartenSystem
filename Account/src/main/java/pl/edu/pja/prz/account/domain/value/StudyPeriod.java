package pl.edu.pja.prz.account.domain.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Embeddable
public class StudyPeriod {

	private LocalDate additionDate;
	private LocalDate endingDate;

}
