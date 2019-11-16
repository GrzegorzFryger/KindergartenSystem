package pl.edu.pja.prz.account.domain;


import pl.edu.pja.prz.account.domain.entity.*;

import java.time.LocalDate;
import java.util.List;


public class AccountAggregate {

	private Long id;
	private String phoneNumber;
	private String email;
	private FullName fullName;
	private Address address;
	private Password password;
	private UserRole userRole;
	private List<ChildrenId> childrenList;


	public AccountAggregate() {
	}

	/*
	 *QUERY
	 */

	//todo

	/*
	 *COMMAND
	 */

	public void updatePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void updateAddress(Address address) {
		this.address = address;
	}


	public boolean appendChild(ChildrenId childrenId) {
		if (childrenList.contains(childrenId)) {
			return false;
		} else {
			childrenList.add(childrenId);
			return true;
		}
	}

	public boolean matchsPasswords(PasswordMenager passwordMenager, CharSequence rawPassword) {
		return passwordMenager.matches(rawPassword, this.password.getCurrentPassword());
	}

	public boolean changePassword(PasswordMenager passwordMenager, CharSequence rawPassword) {
		if (passwordMenager.matches(rawPassword, this.password.getCurrentPassword())) {
			password = new Password(
					LocalDate.now(),
					this.password.getCurrentPassword(),
					passwordMenager.encode(rawPassword)
			);
			return true;
		} else return false;
	}

	/**/


}
