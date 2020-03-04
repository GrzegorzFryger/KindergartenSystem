package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;
	private Set<Role> roles;
	private Employee employee;
	private Person updateData;

	@Mock
	private EmployeeRepository employeeRepository;
	@Mock
	PasswordManager passwordManager;
	@Mock
	private AccountFactory accountFactory;
	@Mock
	private RoleService roleService;

	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		employeeService = new EmployeeService(employeeRepository,accountFactory,passwordManager,roleService);

		address = new Address("70-700","City","Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName","TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";

		this.employee =  new AccountFactoryImpl().createTeacher(new Person(address,fullName,phone),password,email);
	}

//	@Test
//	void createEmployeeAccount() {
//		//given
//		var employee = new AccountFactoryImpl().createTeacher(new Person(address, fullName, phone), password, email);
//
//		//when
//		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
//		when(accountFactory.createStandardAccount(
//				any(Person.class), any(Password.class), any(), any()
//		)).thenReturn(employee);
//		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//		doNothing().when(roleService).persistRoleFromUser(any(Employee.class));
//		var createdGuardian = employeeService.createEmployeeAccount(address, fullName, phone, email);
//
//		//then
//		assertEquals(employee, createdGuardian);
//		verify(passwordManager, times(1)).generateEncodeRandomPassword();
//		verify(accountFactory, times(1))
//				.createStandardAccount(
//						any(Person.class), any(Password.class), any(), any()
//				);
//		verify(employeeRepository, times(1)).save(any());
//		verify(roleService, times(1)).persistRoleFromUser(any());
//
//	}

	@Test
	void createAdministratorAccount() {

		var administrator = new AccountFactoryImpl().createAdministrator(new Person(address, fullName, phone),
				password, email);

		//when
		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
		when(accountFactory.createAdministrator(
				any(Person.class), any(Password.class), any()
		)).thenReturn(administrator);
		when(employeeRepository.save(any(Employee.class))).thenReturn(administrator);
		doNothing().when(roleService).persistRoleFromUser(any(Employee.class));
		var createdAdministrator = employeeService.createAdministratorAccount(address, fullName, phone, email);

		//then
		assertEquals(administrator, createdAdministrator);
		verify(passwordManager, times(1)).generateEncodeRandomPassword();
		verify(accountFactory, times(1))
				.createAdministrator(any(Person.class), any(Password.class), any());
		verify(employeeRepository, times(1)).save(any());
		verify(roleService, times(1)).persistRoleFromUser(any());
	}

	@Test
	void EmployeeServiceNotNull(){
		assertNotNull(employeeService);
	}

//	@Test
//	void should_ReturnTrue_When_EmailAndPasswordMatch() {
//		//given
//
//		//when
//		when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.of( employee));
//		when(passwordManager.matches(anyString(),anyString())).thenReturn(true);
//
//		//then
//		assertTrue(employeeService.signIn("test@test.com", "newPassword"));
//	}
//	@Test
//	void shouldThrowException_When_UserNotFoundInSignIn() {
//		assertThrows(ElementNotFoundException.class,() ->
//				employeeService.signIn("test@test.com", "newPassword")
//		);
//	}

//	@Test
//	void shouldUpdateOnlyChangedPersonalData() {
//		//given
//		var dataToUpdate = new Person( new Address("80-700","UpdateCity","UpdateStreet 256"),
//				new FullName("UpdateTestName","UpdateTestSurname"),
//				new Phone("888888888")
//		);
//
//		//when
//		when(employeeRepository.findById(any())).thenReturn(Optional.of( employee));
//		when(employeeRepository.save(any())).thenReturn(employee);
//		var updatedEmployee = employeeService.updatePersonalData(new UUID(2,6),dataToUpdate);
//
//		//then
//		assertEquals(dataToUpdate.getAddress(),updatedEmployee.getAddress());
//		assertEquals(dataToUpdate.getFullName(),updatedEmployee.getFullName());
//		assertEquals(dataToUpdate.getPhoneNumber(),updatedEmployee.getPhoneNumber());
//	}
//
//	@Test
//	void shouldThrowException_When_UserNotFoundInUpdatePersonalData() {
//		//given
//		var dataToUpdate = new Person( new Address("80-700","UpdateCity","UpdateStreet 256"),
//				new FullName("UpdateTestName","UpdateTestSurname"),
//				new Phone("888888888")
//		);
//
//		assertThrows(ElementNotFoundException.class,() ->
//				employeeService.updatePersonalData(new UUID(2,6),dataToUpdate)
//		);
//	}

//	@Test
//	void shouldUpdateEmailAddress() {
//		//given
//		var dataToUpdate = "updatedemail@test.com";
//
//		//when
//		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
//		when(employeeRepository.save(any())).thenReturn(employee);
//		var updatedEmployee = employeeService.updateEmail(new UUID(1,6),dataToUpdate);
//
//		//then
//		assertEquals(dataToUpdate,updatedEmployee.getEmail());
//	}
//
//	@Test
//	void shouldThrowException_When_UserNotFoundInUpdateEmail(){
//		var dataToUpdate = "updatedemail@test.com";
//
//		assertThrows(ElementNotFoundException.class,() ->
//				employeeService.updateEmail(new UUID(1,6),dataToUpdate)
//		);
//	}

//	@Test
//	void shouldUpdatePassword() {
//		var rawOldPassword = "rawOldPassword";
//		var rawNewPassword = "newPassword";
//
//		//when
//		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
//		when(passwordManager.matches(any(),any())).thenReturn(true);
//		when(passwordManager.encode(any())).thenReturn(rawNewPassword);
//
//		var isSuccess = employeeService.updatePassword(new UUID(2,6),rawOldPassword,rawNewPassword);
//
//		//then
//		assertEquals(rawNewPassword,employee.getPassword().getPassword());
//		assertTrue(isSuccess);
//	}

//	@Test
//	void shouldThrowException_When_UserNotFoundInUpdatePassword(){
//		var rawOldPassword = "rawOldPassword";
//		var rawNewPassword = "newPassword";
//
//		assertThrows(ElementNotFoundException.class,() ->
//				employeeService.updatePassword(new UUID(2,6),rawOldPassword,rawNewPassword)
//		);
//	}

	@Test
	void shouldGetListGorps() {
		//given
		employee.setGroups(Set.of(new Group(1L,"test gropu"),
				new Group(2L,"test gropu")));
		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		var groups = employeeService.getIdGroups(new UUID(1,2));
		//then

		assertEquals(employee.getGroups(),groups);
	}
}