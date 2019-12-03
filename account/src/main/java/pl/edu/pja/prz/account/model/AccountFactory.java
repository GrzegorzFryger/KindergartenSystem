package pl.edu.pja.prz.account.model;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.value.*;

@Service
public interface AccountFactory {

	Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email,
	                       IdentityObject<Long>... groups);

	Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email,
	                        Child... children);

	<T extends  Account> T createStandardAccount(Address address, FullName fullName,
	                                             Phone phone, Password password, String email, Class<T> tClass);
}
