package pl.edu.pja.prz.account.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.enums.PrivilegeType;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountFactoryTest {
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;
	private Set<Role> roles;

	@BeforeEach
	void setUp() {
		address = new Address("70-700","City","Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName","TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";
	}

	@Test
	public void Should_ReturnAdministratorAccountType_When_FactoryCreateAdministrator() {
		//given
		var role = new Role(PrivilegeType.ADMINISTRATOR.toString());
		role.setPrivileges(Set.of(PrivilegeType.ADMINISTRATOR));
		roles = new HashSet<>(Collections.singletonList(role));
		//when
		var administrator = new AccountFactoryImpl().createAdministrator(new Person(address,
				fullName,phone),password,email);

		//then
		assertEquals(EmployeeType.ADMINISTRATOR, administrator.getEmployeeType());
		assertEquals(address, administrator.getAddress());
		assertEquals(phone, administrator.getPhoneNumber());
		assertEquals(fullName, administrator.getFullName());
		assertEquals(email, administrator.getEmail());
		assertEquals(roles, administrator.getRoles());
	}

	@Test
	public void Should_ReturnTeacherAccountType_When_FactoryCreateTeacher() {
		//given
		var role = new Role(PrivilegeType.TEACHER.toString());
		role.setPrivileges(Set.of(PrivilegeType.TEACHER));
		roles = new HashSet<>(Collections.singletonList(role));

		//when
		var teacher =  new AccountFactoryImpl().createTeacher(new Person(address,fullName,phone),password,email);

		//then
		assertEquals(Employee.class, teacher.getClass());
		assertEquals(address, teacher.getAddress());
		assertEquals(phone, teacher.getPhoneNumber());
		assertEquals(fullName, teacher.getFullName());
		assertEquals(email, teacher.getEmail());
		assertEquals(roles, teacher.getRoles());
	}

	@Test
	public void Should_ReturnGuardianAccountType_When_FactoryCreateTeacher() {
		//given
		var role = new Role(PrivilegeType.USER.toString());
		role.setPrivileges(Set.of(PrivilegeType.USER));
		roles = new HashSet<>(Collections.singletonList(role));

		//when
		var guardian = new AccountFactoryImpl().createGuardian(new Person(address,fullName,phone),password,email);

		//then
		assertEquals(Guardian.class, guardian.getClass());
		assertEquals(address, guardian.getAddress());
		assertEquals(phone, guardian.getPhoneNumber());
		assertEquals(fullName, guardian.getFullName());
		assertEquals(email, guardian.getEmail());
		assertEquals(roles, guardian.getRoles());
	}
}