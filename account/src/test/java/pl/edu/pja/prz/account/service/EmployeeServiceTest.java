package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.AdministratorAccountFactoryImpl;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock private EmployeeRepository employeeRepository;
	@Mock PasswordManager passwordManager;
	private EmployeeService employeeService;
	private Employee employee;
	private Person updateData;

	@BeforeEach
	void setUp() {
		employeeService = new EmployeeService(passwordManager,employeeRepository);

		var address = new Address("70-700","City","Street 256");
		var phone = new Phone("123132123");
		var fullName = new FullName("TestName","TestSurname");
		var password = new Password("newPassword");
		var email = "test@test.com";

		this.employee =  new AdministratorAccountFactoryImpl().createTeacher(address,fullName,phone,password,email);
	}

	@Test
	void EmployeeServiceNotNull(){
		assertNotNull(employeeService);
	}

	@Test
	void should_ReturnTrue_When_EmailAndPasswordMatch() {
		//given

		//when
		when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.of( employee));
		when(passwordManager.matches(anyString(),anyString())).thenReturn(true);

		//then
		assertTrue(employeeService.signIn("test@test.com", "newPassword"));
	}
	@Test
	void shouldThrowException_When_UserNotFoundInSignIn() {
		assertThrows(IllegalArgumentException.class,() ->
				employeeService.signIn("test@test.com", "newPassword")
		);
	}

	@Test
	void shouldUpdateOnlyChangedPersonalData() {
		//given
		var dataToUpdate = new Person( new Address("80-700","UpdateCity","UpdateStreet 256"),
				new FullName("UpdateTestName","UpdateTestSurname"),
				new Phone("888888888")
		);

		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of( employee));
		when(employeeRepository.save(any())).thenReturn(employee);
		var updatedEmployee = employeeService.updatePersonalData(new UUID(2,6),dataToUpdate);

		//then
		assertEquals(dataToUpdate.getAddress(),updatedEmployee.getAddress());
		assertEquals(dataToUpdate.getFullName(),updatedEmployee.getFullName());
		assertEquals(dataToUpdate.getPhoneNumber(),updatedEmployee.getPhoneNumber());
	}

	@Test
	void shouldThrowException_When_UserNotFoundInUpdatePersonalData() {
		//given
		var dataToUpdate = new Person( new Address("80-700","UpdateCity","UpdateStreet 256"),
				new FullName("UpdateTestName","UpdateTestSurname"),
				new Phone("888888888")
		);

		assertThrows(IllegalArgumentException.class,() ->
				employeeService.updatePersonalData(new UUID(2,6),dataToUpdate)
		);
	}

	@Test
	void shouldUpdateEmailAddress() {
		//given
		var dataToUpdate = "updatedemail@test.com";

		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(any())).thenReturn(employee);
		var updatedEmployee = employeeService.updateEmail(new UUID(1,6),dataToUpdate);

		//then
		assertEquals(dataToUpdate,updatedEmployee.getEmail());
	}

	@Test
	void shouldThrowException_When_UserNotFoundInUpdateEmail(){
		var dataToUpdate = "updatedemail@test.com";

		assertThrows(IllegalArgumentException.class,() ->
				employeeService.updateEmail(new UUID(1,6),dataToUpdate)
		);
	}

	@Test
	void shouldUpdatePassword() {
		var rawOldPassword = "rawOldPassword";
		var rawNewPassword = "newPassword";

		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		when(passwordManager.matches(any(),any())).thenReturn(true);
		when(passwordManager.encode(any())).thenReturn(rawNewPassword);

		var isSuccess = employeeService.updatePassword(new UUID(2,6),rawOldPassword,rawNewPassword);

		//then
		assertEquals(rawNewPassword,employee.getPassword().getPassword());
		assertTrue(isSuccess);
	}

	@Test
	void shouldThrowException_When_UserNotFoundInUpdatePassword(){
		var rawOldPassword = "rawOldPassword";
		var rawNewPassword = "newPassword";

		assertThrows(IllegalArgumentException.class,() ->
				employeeService.updatePassword(new UUID(2,6),rawOldPassword,rawNewPassword)
		);
	}

	@Test
	void shouldGetListGorps() {
		//given
		employee.setGroups(Set.of(new IdentityObject<>(1L,"test gropu"),
				new IdentityObject<>(2L,"test gropu")));
		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		var groups = employeeService.getIdGrups(new UUID(1,2));
		//then

		assertEquals(employee.getGroups(),groups);
	}
}