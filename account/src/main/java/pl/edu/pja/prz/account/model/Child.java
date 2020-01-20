package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.BaseEntityUuid;
import pl.edu.pja.prz.commons.model.FullName;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Child)) return false;
		if (!super.equals(o)) return false;

		Child child = (Child) o;

		if (getAddress() != null ? !getAddress().equals(child.getAddress()) : child.getAddress() != null) return false;
		if (getAge() != null ? !getAge().equals(child.getAge()) : child.getAge() != null) return false;
		if (getBorough() != null ? !getBorough().equals(child.getBorough()) : child.getBorough() != null) return false;
		if (getFullName() != null ? !getFullName().equals(child.getFullName()) : child.getFullName() != null)
			return false;
		if (getGender() != child.getGender()) return false;
		if (getChildStatuses() != null ? !getChildStatuses().equals(child.getChildStatuses()) : child.getChildStatuses() != null)
			return false;
		if (getGuardians() != null ? !getGuardians().equals(child.getGuardians()) : child.getGuardians() != null)
			return false;
		if (getPeselNumber() != null ? !getPeselNumber().equals(child.getPeselNumber()) : child.getPeselNumber() != null)
			return false;
		return getStudyPeriod() != null ? getStudyPeriod().equals(child.getStudyPeriod()) : child.getStudyPeriod() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
		result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
		result = 31 * result + (getBorough() != null ? getBorough().hashCode() : 0);
		result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
		result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
		result = 31 * result + (getChildStatuses() != null ? getChildStatuses().hashCode() : 0);
		result = 31 * result + (getGuardians() != null ? getGuardians().hashCode() : 0);
		result = 31 * result + (getPeselNumber() != null ? getPeselNumber().hashCode() : 0);
		result = 31 * result + (getStudyPeriod() != null ? getStudyPeriod().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Child{" +
				"address=" + address +
				", age=" + age +
				", borough=" + borough +
				", fullName=" + fullName +
				", gender=" + gender +
				", childStatuses=" + childStatuses +
				", guardians=" + guardians +
				", peselNumber='" + peselNumber + '\'' +
				", studyPeriod=" + studyPeriod +
				'}';
	}
}
