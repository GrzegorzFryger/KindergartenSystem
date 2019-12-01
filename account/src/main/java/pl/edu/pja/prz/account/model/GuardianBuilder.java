package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import java.util.Set;

public final class GuardianBuilder {
	private Set<Child> children;
	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private Set<Role> roles;

	private GuardianBuilder() {
	}

	public static GuardianBuilder aGuardian() {
		return new GuardianBuilder();
	}

	public GuardianBuilder withChildren(Set<Child> children) {
		this.children = children;
		return this;
	}

	public GuardianBuilder withPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public GuardianBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public GuardianBuilder withFullName(FullName fullName) {
		this.fullName = fullName;
		return this;
	}

	public GuardianBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}

	public GuardianBuilder withPassword(Password password) {
		this.password = password;
		return this;
	}

	public GuardianBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public Guardian build() {
		Guardian guardian = AccountBuilder.anAccount()
				.withPhoneNumber(phoneNumber)
				.withAddress(address)
				.withEmail(email)
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.build(Guardian.class);

		guardian.setChildren(children);

		return guardian;
	}
}
