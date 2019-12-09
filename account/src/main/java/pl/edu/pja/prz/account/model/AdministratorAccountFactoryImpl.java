package pl.edu.pja.prz.account.model;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.enums.PrivilegeType;
import pl.edu.pja.prz.account.model.value.Password;

@Component
public class AdministratorAccountFactoryImpl extends StandardAccountFactoryImpl implements AdministratorAccountFactory {
	private final PrivilegeType ADMIN_PRIVILEGE = PrivilegeType.ADMINISTRATOR;

	public AdministratorAccountFactoryImpl() {
	}

	@Override
	public Employee createAdministrator(Person person, Password password, String email) {
		var role = new Role(ADMIN_PRIVILEGE.toString());
		role.addPrivilege(ADMIN_PRIVILEGE);

		var administrator = new Employee(person.getAddress(), person.getFullName(),
				person.getPhoneNumber(), password, email, EmployeeType.ADMINISTRATOR
		);

		role.addAccount(administrator);
		administrator.setAccountStatus(AccountStatus.ACTIVE);
		return administrator;
	}

}
