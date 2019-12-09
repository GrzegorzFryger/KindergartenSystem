package pl.edu.pja.tuition.model;

import pl.edu.pja.tuition.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tuition {
	private Long id;
	private Child child;
	private BigDecimal amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private Status status;



}
