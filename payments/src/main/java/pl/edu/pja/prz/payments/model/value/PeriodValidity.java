package pl.edu.pja.prz.payments.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PeriodValidity {
	private LocalDate startDate;
	private LocalDate endDate;

	PeriodValidity() {
	}

	public PeriodValidity(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
