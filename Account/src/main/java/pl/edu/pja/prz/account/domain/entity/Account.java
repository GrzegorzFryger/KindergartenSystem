package pl.edu.pja.prz.account.domain.entity;


import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.FullName;
import pl.edu.pja.prz.account.domain.value.Password;
import pl.edu.pja.prz.account.domain.value.Phone;

import java.util.UUID;


public class Account extends BaseEntity<UUID> {

	private Phone phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private UserRole userRole;

	public Account() {
	}

}
