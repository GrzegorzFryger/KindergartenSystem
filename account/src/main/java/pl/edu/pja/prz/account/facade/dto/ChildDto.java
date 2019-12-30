package pl.edu.pja.prz.account.facade.dto;

import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class ChildDto {
	private UUID id;
	private String name;
	private String surname;
	private String pesel;
	private Gender gender;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate dateOfBirth;
	private Long boroughId;

	public UUID getId() {
		return id;
	}

	public Long getBoroughId() {
		return boroughId;
	}

	public void setBoroughId(Long boroughId) {
		this.boroughId = boroughId;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
