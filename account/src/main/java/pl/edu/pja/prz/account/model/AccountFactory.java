package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Password;

public interface AccountFactory {

	Employee createTeacher(Person person, Password password, String email, Group... groups);

	Employee createTeacher(Person person, Password password, String email);

	Guardian createGuardian(Person person, Password password, String email, Child... children);

	Guardian createGuardian(Person person, Password password, String email);

	Employee createAdministrator(Person person, Password password, String email);

	default <T extends Account> T createStandardAccount(Person person, Password password,
	                                                    String email, Class<T> tClass) {
		if (tClass.getTypeName().equals(Employee.class.getName())) {
			return tClass.cast(createTeacher(person, password, email));
		} else if (tClass.getTypeName().equals(Guardian.class.getName())) {
			return tClass.cast(createGuardian(person, password, email));
		} else throw new IllegalArgumentException("Not Found " + tClass.getName() + " type ");
	}


}
