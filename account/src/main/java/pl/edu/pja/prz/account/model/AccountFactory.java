package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.*;

import java.util.Set;

public class AccountFactory implements AccountAbstractFactory {

	private final PrivilegeType ADMIN_PRIVILEGE = PrivilegeType.ADMINISTRATOR;
	private final PrivilegeType USER_PRIVILEGE = PrivilegeType.USER;
	private final PrivilegeType TEACHER_PRIVILEGE = PrivilegeType.TEACHER;

	@Override
	public Administrator createAdministrator(String email, Phone phone, Password password, FullName fullName,
	                                         Address address) {
		var role = new Role(ADMIN_PRIVILEGE.toString(),Set.of(ADMIN_PRIVILEGE));

		return AdministratorBuilder
				.anAdministrator()
				.withPhoneNumber(phone)
				.withRoles(Set.of(role))
				.withFullName(fullName)
				.withEmail(email)
				.withPassword(password)
				.withAddress(address)
				.build();
	}

	@Override
	public Teacher createTeacher(String email, Phone phone, Password password, FullName fullName,
	                             Address address, IdentityObject<Long>... groups) {
		var teacher = createTeacher(email, phone, password, fullName, address);
		teacher.setGroups(Set.of(groups));
		return teacher;
	}

	@Override
	public Teacher createTeacher(String email, Phone phone, Password password, FullName fullName,
	                             Address address) {
		var role = new Role(TEACHER_PRIVILEGE.toString(),Set.of(TEACHER_PRIVILEGE));

		return TeacherBuilder.aTeacher()
				.withPhoneNumber(phone)
				.withRoles(Set.of(role))
				.withFullName(fullName)
				.withEmail(email)
				.withPassword(password)
				.withAddress(address)
				.build();
	}


	@Override
	public Guardian createGuardian(String email, Phone phone, Password password, FullName fullName,
	                               Address address,Child... children ) {
		var guardian = createGuardian(email, phone, password, fullName, address);
		guardian.setChildren(Set.of(children));

		return guardian;
	}

	@Override
	public Guardian createGuardian(String email, Phone phone, Password password, FullName fullName,
	                               Address address) {
		var role = new Role(USER_PRIVILEGE.toString(),Set.of(USER_PRIVILEGE));

		return GuardianBuilder.aGuardian()
				.withPhoneNumber(phone)
				.withRoles(Set.of(role))
				.withFullName(fullName)
				.withEmail(email)
				.withPassword(password)
				.withAddress(address)
				.build();
	}
}
