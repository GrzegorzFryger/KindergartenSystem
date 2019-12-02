package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.enums.PrivilegeType;
import pl.edu.pja.prz.account.model.value.*;

import java.util.Arrays;
import java.util.Set;

public class AccountFactoryImpl extends AccountAbstractFactory implements AccountFactory {

	private final PrivilegeType ADMIN_PRIVILEGE = PrivilegeType.ADMINISTRATOR;
	private final PrivilegeType USER_PRIVILEGE = PrivilegeType.USER;
	private final PrivilegeType TEACHER_PRIVILEGE = PrivilegeType.TEACHER;

	@Override
	public Employee createAdministrator(Address address, FullName fullName,
	                                    Phone phone, Password password, String email) {
		var roles = Set.of(new Role(ADMIN_PRIVILEGE.toString(),Set.of(ADMIN_PRIVILEGE)));
		var administrator = creteEmployee(address,fullName,phone,password,email, EmployeeType.ADMINISTRATOR);

		roles.forEach(administrator::addRole);

		administrator.setAccountStatus(AccountStatus.ACCTIVE);
		return administrator;
	}

	@Override
	public Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email,
	                              IdentityObject<Long>... groups) {
		var teacher = createTeacher(address,fullName,phone,password,email);

		Arrays.stream(groups).forEach(teacher::addGrup);
		return teacher;
	}

	@Override
	public Employee createTeacher(Address address, FullName fullName, Phone phone, Password password, String email) {
		var roles = Set.of(new Role(TEACHER_PRIVILEGE.toString(),Set.of(TEACHER_PRIVILEGE)));
		Employee teacher = creteEmployee(address,fullName,phone,password,email, EmployeeType.TEACHER);

		teacher.setRoles(roles);
		teacher.setAccountStatus(AccountStatus.ACCTIVE);
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
		var role = Set.of(new Role(USER_PRIVILEGE.toString(),Set.of(USER_PRIVILEGE)));
		var guardian =new Guardian(address,fullName,phone,password,email);

		role.forEach(guardian::addRole);
		return guardian;
	}

}
