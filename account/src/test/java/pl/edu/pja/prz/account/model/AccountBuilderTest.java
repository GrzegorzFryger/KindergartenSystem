package pl.edu.pja.prz.account.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountBuilderTest {

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
		roles = new HashSet<>(Collections.singletonList(new Role()));
	}

	@Test
	public void Should_CreateAdministratorAccountType_When_BuilderCreatedInstance() {
		//when
		var administrator = AdministratorBuilder
				.anAdministrator()
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.withAddress(address)
				.withPhoneNumber(phone)
				.withEmail(email).build();

		//then
		assertEquals(Administrator.class, administrator.getClass());
		assertEquals(address, administrator.getAddress());
		assertEquals(phone, administrator.getPhoneNumber());
		assertEquals(fullName, administrator.getFullName());
		assertEquals(email, administrator.getEmail());
		assertEquals(roles, administrator.getRoles());
	}

	@Test
	public void Should_CreateTeacherAccountType_When_BuilderCreatedInstance() {
		//when
		var teacher = TeacherBuilder.aTeacher()
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.withAddress(address)
				.withPhoneNumber(phone)
				.withEmail(email).build();

		//then
		assertEquals(Teacher.class, teacher.getClass());
		assertEquals(address, teacher.getAddress());
		assertEquals(phone, teacher.getPhoneNumber());
		assertEquals(fullName, teacher.getFullName());
		assertEquals(email, teacher.getEmail());
		assertEquals(roles, teacher.getRoles());
	}

	@Test
	public void Should_CreateGuardianAccountType_When_BuilderCreatedInstance() {
		//when
		var guardian = GuardianBuilder.aGuardian()
				.withFullName(fullName)
				.withPassword(password)
				.withRoles(roles)
				.withAddress(address)
				.withPhoneNumber(phone)
				.withEmail(email).build();

		//then
		assertEquals(Guardian.class, guardian.getClass());
		assertEquals(address, guardian.getAddress());
		assertEquals(phone, guardian.getPhoneNumber());
		assertEquals(fullName, guardian.getFullName());
		assertEquals(email, guardian.getEmail());
		assertEquals(roles, guardian.getRoles());
	}
}