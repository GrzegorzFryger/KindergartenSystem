package pl.edu.pja.prz.account.facade.dto;

import java.time.LocalDate;

public class StudyPeriodDto {
	private LocalDate startDate;
	private LocalDate endDate;

	public StudyPeriodDto() {
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
