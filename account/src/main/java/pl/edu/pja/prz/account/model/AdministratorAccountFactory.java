package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Password;

public interface AdministratorAccountFactory extends AccountFactory {
	Employee createAdministrator(Person person, Password password, String email);
}
