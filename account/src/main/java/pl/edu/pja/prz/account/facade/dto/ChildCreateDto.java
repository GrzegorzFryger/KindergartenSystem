package pl.edu.pja.prz.account.facade.dto;

import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.LocalDate;

public class ChildCreateDto {
	private String name;
	private String surname;
	private String pesel;
	private Gender gender;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate dateOfBirth;
}
