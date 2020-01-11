package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
public class StudyPeriod {
	private LocalDate additionDate;
	private LocalDate endingDate;

	public StudyPeriod() {
	}

	public StudyPeriod(LocalDate additionDate, LocalDate endingDate) {
		this.additionDate = additionDate;
		this.endingDate = endingDate;
	}

	public LocalDate getAdditionDate() {
		return additionDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setAdditionDate(LocalDate additionDate) {
		this.additionDate = additionDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof StudyPeriod)) return false;

		StudyPeriod that = (StudyPeriod) o;

		if (getAdditionDate() != null ? !getAdditionDate().equals(that.getAdditionDate()) : that.getAdditionDate() != null)
			return false;
		return getEndingDate() != null ? getEndingDate().equals(that.getEndingDate()) : that.getEndingDate() == null;
	}

	@Override
	public int hashCode() {
		int result = getAdditionDate() != null ? getAdditionDate().hashCode() : 0;
		result = 31 * result + (getEndingDate() != null ? getEndingDate().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StudyPeriod{" +
				"additionDate=" + additionDate +
				", endingDate=" + endingDate +
				'}';
	}
}
