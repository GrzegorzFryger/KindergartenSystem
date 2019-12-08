package pl.edu.pja.prz.account.model;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.enums.PrivilegeType;
import pl.edu.pja.prz.account.model.value.*;

import java.util.Arrays;

@Component
public class StandardAccountFactoryImpl implements AccountFactory  {
	private final PrivilegeType USER_PRIVILEGE = PrivilegeType.USER;
	private final PrivilegeType TEACHER_PRIVILEGE = PrivilegeType.TEACHER;

	public StandardAccountFactoryImpl() {
	}

	@Override
	public Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email,
	                              IdentityObject<Long>... groups) {
		var teacher = createTeacher(address, fullName, phone, password, email);

		Arrays.stream(groups).forEach(teacher::addGrup);
		return teacher;
	}

	@Override
	public Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email) {
		var role = new Role(TEACHER_PRIVILEGE.toString());
		role.addPrivilege(TEACHER_PRIVILEGE);

		var teacher = new Employee(address,fullName,phone,password,email, EmployeeType.TEACHER);

		role.addAccount(teacher);
		teacher.setAccountStatus(AccountStatus.ACTIVE);
		return teacher;
	}

	@Override
	public Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email,
	                               Child... children) {
		var guardian = createGuardian(address,fullName,phone,password,email);

		Arrays.stream(children).forEach(guardian::addChild);
		return guardian;
	}

	@Override
	public Guardian createGuardian(Address address, FullName fullName, Phone phone, Password password, String email) {

		var role = new Role(USER_PRIVILEGE.toString());
		role.addPrivilege(USER_PRIVILEGE);
		var guardian = new Guardian(address,fullName,phone,password,email);

		role.addAccount(guardian);
		guardian.setAccountStatus(AccountStatus.NOT_ACTIVE);
		return guardian;
	}

}
