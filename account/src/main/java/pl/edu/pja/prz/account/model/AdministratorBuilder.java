package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import java.util.Set;

public final class AdministratorBuilder {
	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private Set<Role> roles;

	private AdministratorBuilder() {
	}

	public static AdministratorBuilder anAdministrator() {
		return new AdministratorBuilder();
	}

	public AdministratorBuilder withPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public AdministratorBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public AdministratorBuilder withFullName(FullName fullName) {
		this.fullName = fullName;
		return this;
	}

	public AdministratorBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}

	public AdministratorBuilder withPassword(Password password) {
		this.password = password;
		return this;
	}

	public AdministratorBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public Administrator build() {
		return AccountBuilder.anAccount()
				.withPhoneNumber(phoneNumber)
				.withAddress(address)
				.withEmail(email)
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.build(Administrator.class);
	}
}
