package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.FullName;

public class Children {

	private Long id;
	private FullName fullName;
	private Address address;
	private Long peselNumber;
	private StudyPeriod studyPeriod;
	private Borough borough;
	private Status status;
	private Tution tution;
	private Absence absence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(Long peselNumber) {
		this.peselNumber = peselNumber;
	}

	public StudyPeriod getStudyPeriod() {
		return studyPeriod;
	}

	public void setStudyPeriod(StudyPeriod studyPeriod) {
		this.studyPeriod = studyPeriod;
	}

	public Borough getBorough() {
		return borough;
	}

	public void setBorough(Borough borough) {
		this.borough = borough;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tution getTution() {
		return tution;
	}

	public void setTution(Tution tution) {
		this.tution = tution;
	}

	public Absence getAbsence() {
		return absence;
	}

	public void setAbsence(Absence absence) {
		this.absence = absence;
	}
}
