package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

final class AccountBuilder {
	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private Set<Role> roles;

	private AccountBuilder() {
	}

	static AccountBuilder anAccount() {
		return new AccountBuilder();
	}

	 AccountBuilder withPhoneNumber(Phone phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	 AccountBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	 AccountBuilder withFullName(FullName fullName) {
		this.fullName = fullName;
		return this;
	}

	 AccountBuilder withAddress(Address address) {
		this.address = address;
		return this;
	}

	 AccountBuilder withPassword(Password password) {
		this.password = password;
		return this;
	}

	 AccountBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	 <T extends Account> T build( Class<T> type) {
		T account = null;
		try {
			account = type.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException |
				InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		account.setPhoneNumber(phoneNumber);
		account.setEmail(email);
		account.setFullName(fullName);
		account.setAddress(address);
		account.setPassword(password);
		account.setRoles(roles);
		return account;
	}
}
