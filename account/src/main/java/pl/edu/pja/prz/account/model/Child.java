package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Child extends BaseEntityUuid {
	private Address address;
	private Age age;
	@ManyToOne
	@JoinColumn(name = "fk_borough")
	private Borough borough;
	private FullName fullName;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<ChildStatus> childStatuses = new HashSet<>();
	@ManyToMany(mappedBy = "children")
	private Set<Guardian> guardians = new HashSet<>();
	private String peselNumber;
	private StudyPeriod studyPeriod;

	public Child() {
	}

	public Child(Set<ChildStatus> childStatuses, Gender gender, Borough borough, Set<Guardian> guardians,
	             String peselNumber, FullName fullName, Age age, Address address, StudyPeriod studyPeriod) {
		this.address = address;
		this.age = age;
		this.borough = borough;
		this.childStatuses = childStatuses;
		this.fullName = fullName;
		this.gender = gender;
		this.guardians = guardians;
		this.peselNumber = peselNumber;
		this.studyPeriod = studyPeriod;
	}

	public Child(Gender gender, Borough borough, String peselNumber, FullName fullName, Age age, Address address,
	             StudyPeriod studyPeriod) {
		this.address = address;
		this.age = age;
		this.borough = borough;
		this.fullName = fullName;
		this.gender = gender;
		this.peselNumber = peselNumber;
		this.studyPeriod = studyPeriod;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Borough getBorough() {
		return borough;
	}

	public void setBorough(Borough borough) {
		this.borough = borough;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<ChildStatus> getChildStatuses() {
		return childStatuses;
	}

	public void setChildStatuses(Set<ChildStatus> childStatuses) {
		this.childStatuses = childStatuses;
	}

	public Set<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<Guardian> guardians) {
		this.guardians = guardians;
	}

	public String getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(String peselNumber) {
		this.peselNumber = peselNumber;
	}

	public StudyPeriod getStudyPeriod() {
		return studyPeriod;
	}

	public void setStudyPeriod(StudyPeriod studyPeriod) {
		this.studyPeriod = studyPeriod;
	}

	public boolean hasStatus(ChildStatus... status) {
		return (status.length > 1) && childStatuses.containsAll(Arrays.asList(status));
	}


}
