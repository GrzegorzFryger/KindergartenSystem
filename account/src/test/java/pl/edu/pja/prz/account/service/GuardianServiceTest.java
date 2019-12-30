package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

class GuardianServiceTest {
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;




	@BeforeEach
	void setUp() {
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";
	}

	@Test
	void createGuardianAccount() {
//		//given
//		var guardian = new AdministratorAccountFactoryImpl().createGuardian(new Person(address, fullName, phone), password, email);
//
//		//when
//		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
//		when(administratorAccountFactory.createStandardAccount(
//				any(Person.class), any(Password.class), any(), any()
//		)).thenReturn(guardian);
//		when(guardianRepository.save(any(Guardian.class))).thenReturn(guardian);
//		doNothing().when(roleService).persistRoleFromUser(any(Guardian.class));
//		var createdGuardian = administratorService.createGuardianAccount(address, fullName, phone, email);
//
//		//then
//		assertEquals(guardian, createdGuardian);
//		verify(passwordManager, times(1)).generateEncodeRandomPassword();
//		verify(administratorAccountFactory, times(1))
//				.createStandardAccount(
//						any(Person.class), any(Password.class), any(), any()
//				);
//		verify(guardianRepository, times(1)).save(any());
//		verify(roleService, times(1)).persistRoleFromUser(any());

	}
}