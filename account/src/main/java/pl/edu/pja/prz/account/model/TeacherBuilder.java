package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

import java.util.Set;

public final class TeacherBuilder {
	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private Set<IdentityObject<Long>> groups;
	private Set<Role> roles;

	private TeacherBuilder() {
	}

	public static TeacherBuilder aTeacher() {
		return new TeacherBuilder();
	}

	public TeacherBuilder withPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public TeacherBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public TeacherBuilder withFullName(FullName fullName) {
		this.fullName = fullName;
		return this;
	}

	public TeacherBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}

	public TeacherBuilder withPassword(Password password) {
		this.password = password;
		return this;
	}

	public TeacherBuilder withGroups(Set<IdentityObject<Long>> groups) {
		this.groups = groups;
		return this;
	}

	public TeacherBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public Teacher build() {
		Teacher teacher = AccountBuilder.anAccount()
				.withPhoneNumber(phoneNumber)
				.withAddress(address)
				.withEmail(email)
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.build(Teacher.class);
		teacher.setGroups(groups);
		return teacher;
	}
}
