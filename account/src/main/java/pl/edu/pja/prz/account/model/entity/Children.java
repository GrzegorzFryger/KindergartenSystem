package pl.edu.pja.prz.account.model.entity;

import pl.edu.pja.prz.account.model.value.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Children extends BaseEntity<UUID> {

	private Long peselNumber;
	@ManyToOne
	private Status status;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private FullName fullName;
	private Age age;
	private Address address;
	private StudyPeriod studyPeriod;
	@ManyToOne
	private Borough borough;

	public Children() {
	}

	public Long getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(Long peselNumber) {
		this.peselNumber = peselNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
}
