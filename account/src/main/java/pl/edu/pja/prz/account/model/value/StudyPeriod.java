package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
public class StudyPeriod {

	private LocalDate additionDate;
	private LocalDate endingDate;

	public StudyPeriod() {
	}

	public LocalDate getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(LocalDate additionDate) {
		this.additionDate = additionDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
}
