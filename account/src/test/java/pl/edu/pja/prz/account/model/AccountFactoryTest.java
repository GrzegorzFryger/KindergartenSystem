package pl.edu.pja.prz.account.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.enums.PrivilegeType;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import java.time.LocalDate;
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
		password = new Password(LocalDate.now(),"oldPassword","newPassword");
		email = "test@test.com";
	}

	@Test
	public void Should_ReturnAdministratorAccountType_When_FactoryCreateAdministrator() {
		//given
		roles = new HashSet<>(Collections.singletonList(new Role(PrivilegeType.ADMINISTRATOR.toString(),
				Set.of(PrivilegeType.ADMINISTRATOR))));
		//when
		var administrator = new AccountFactoryImpl().createAdministrator(address,fullName,phone,password,email);

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
		roles = new HashSet<>(Collections.singletonList(new Role(PrivilegeType.TEACHER.toString(),
				Set.of(PrivilegeType.TEACHER))));

		//when
		var teacher = new AccountFactoryImpl().createTeacher(address,fullName,phone,password,email);

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
		roles = new HashSet<>(Collections.singletonList(new Role(PrivilegeType.USER.toString(),
				Set.of(PrivilegeType.USER))));

		//when
		var guardian = new AccountFactoryImpl().createGuardian(address,fullName,phone,password,email);

		//then
		assertEquals(Guardian.class, guardian.getClass());
		assertEquals(address, guardian.getAddress());
		assertEquals(phone, guardian.getPhoneNumber());
		assertEquals(fullName, guardian.getFullName());
		assertEquals(email, guardian.getEmail());
		assertEquals(roles, guardian.getRoles());
	}
}