package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Entity
public class Child extends BaseEntity<UUID> {

	private Long peselNumber;
	private FullName fullName;
	private Age age;
	private Address address;
	private StudyPeriod studyPeriod;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<ChildStatus> childStatuses;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "fk_borough")
	private Borough borough;

	@ManyToMany(mappedBy = "children")
	private Set<Guardian> guardians;

	Child() {
	}

	public Child(Long peselNumber, FullName fullName, Age age, Address address, StudyPeriod studyPeriod,
	             Set<ChildStatus> childStatuses, Gender gender, Borough borough, Set<Guardian> guardians) {
		this.peselNumber = peselNumber;
		this.fullName = fullName;
		this.age = age;
		this.address = address;
		this.studyPeriod = studyPeriod;
		this.childStatuses = childStatuses;
		this.gender = gender;
		this.borough = borough;
		this.guardians = guardians;
	}

	public Long getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(Long peselNumber) {
		this.peselNumber = peselNumber;
	}

	public Set<ChildStatus> getChildStatuses() {
		return childStatuses;
	}

	public void setChildStatuses(Set<ChildStatus> childStatuses) {
		this.childStatuses = childStatuses;
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

	public Set<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<Guardian> guardians) {
		this.guardians = guardians;
	}

	public boolean hasStatus(ChildStatus... status) {
		return (status.length > 1) && childStatuses.containsAll(Arrays.asList(status));
	}


}
