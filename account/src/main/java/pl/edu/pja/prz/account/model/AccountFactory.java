package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

public interface AccountFactory {
	Employee createAdministrator(Address address, FullName fullName, Phone phone, Password password, String email);
	Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email,
	                       IdentityObject<Long>... groups);
	Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email);
	Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email,
	                        Child... children);
	Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email);
}
