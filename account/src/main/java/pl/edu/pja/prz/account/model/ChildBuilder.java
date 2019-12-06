package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;

import java.util.Set;

public final class ChildBuilder {
	private Address address;
	private Age age;
	private Borough borough;
	private FullName fullName;
	private Gender gender;
	private Set<ChildStatus> childStatuses;
	private Set<Guardian> guardians;
	private String peselNumber;
	private StudyPeriod studyPeriod;

	private ChildBuilder() {
	}

	public static ChildBuilder aChild() {
		return new ChildBuilder();
	}

	public ChildBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}

	public ChildBuilder withAge(Age age) {
		this.age = age;
		return this;
	}

	public ChildBuilder withBorough(Borough borough) {
		this.borough = borough;
		return this;
	}

	public ChildBuilder withFullName(FullName fullName) {
		this.fullName = fullName;
		return this;
	}

	public ChildBuilder withGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public ChildBuilder withChildStatuses(Set<ChildStatus> childStatuses) {
		this.childStatuses = childStatuses;
		return this;
	}

	public ChildBuilder withGuardians(Set<Guardian> guardians) {
		this.guardians = guardians;
		return this;
	}

	public ChildBuilder withPeselNumber(String peselNumber) {
		this.peselNumber = peselNumber;
		return this;
	}

	public ChildBuilder withStudyPeriod(StudyPeriod studyPeriod) {
		this.studyPeriod = studyPeriod;
		return this;
	}

	public Child build() {
		Child child = new Child();
		child.setAddress(address);
		child.setAge(age);
		child.setBorough(borough);
		child.setFullName(fullName);
		child.setGender(gender);
		child.setChildStatuses(childStatuses);
		child.setGuardians(guardians);
		child.setPeselNumber(peselNumber);
		child.setStudyPeriod(studyPeriod);
		return child;
	}
}
