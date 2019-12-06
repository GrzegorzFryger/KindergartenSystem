package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

public interface AccountFactory {
	Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email,
	                       IdentityObject<Long>... groups);

	Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email,
	                        Child... children);

	Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email);

	Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email);

	default public <T extends  Account> T createStandardAccount(Address address, FullName fullName,
	                                                            Phone phone, Password password, String email, Class<T> tClass) {
		if(tClass.getTypeName().equals(Employee.class.getName())) {
			return tClass.cast(createTeacher(address, fullName, phone, password, email));
		}
		else if(tClass.getTypeName().equals(Guardian.class.getName())) {
			return tClass.cast(createGuardian(address, fullName, phone, password, email));
		}
		else throw new IllegalArgumentException("Not Found " + tClass.getName() + " type ");
	}

}
