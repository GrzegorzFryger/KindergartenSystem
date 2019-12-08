package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.AdministratorAccountFactoryImpl;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Optional;
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
	void shouldUpdateEmailAddress() {

		var dataToUpdate = "updatedemail@test.com";

		//when
		when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(any())).thenReturn(employee);
		var updatedEmployee = employeeService.updateEmail(new UUID(2,6),dataToUpdate);

		//then
		assertEquals(dataToUpdate,updatedEmployee.getEmail());

	}

	@Test
	void shouldUpdatePassword() {


	}

	@Test
	void getIdGrups() {
	}
}