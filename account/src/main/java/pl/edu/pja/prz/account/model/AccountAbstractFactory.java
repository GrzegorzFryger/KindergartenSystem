package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

public interface AccountAbstractFactory {


	Administrator createAdministrator(String email, Phone phone, Password password, FullName fullName,
	                                  Address address);

	Teacher createTeacher(String email, Phone phone, Password password, FullName fullName,
	                      Address address, IdentityObject<Long>... groups);

	Teacher createTeacher(String email, Phone phone, Password password, FullName fullName,
	                      Address address);

	Guardian createGuardian(String email, Phone phone, Password password, FullName fullName,
	                        Address address, Child... children);

	Guardian createGuardian(String email, Phone phone, Password password, FullName fullName,
	                        Address address);
}
