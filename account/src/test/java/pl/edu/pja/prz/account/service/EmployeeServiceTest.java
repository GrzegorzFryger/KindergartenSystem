package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.AdministratorAccountFactoryImpl;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Role;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock private EmployeeRepository employeeRepository;
	@Mock PasswordManager passwordManager;
	@Captor
	private ArgumentCaptor<List<Object>> captor;
	@Captor
	private ArgumentCaptor<List<CharSequence>> captor1;
	private EmployeeService employeeService;

	private Employee employee;
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;
	private Set<Role> roles;

	@BeforeEach
	void setUp() {
		employeeService = new EmployeeService(passwordManager,employeeRepository);

		address = new Address("70-700","City","Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName","TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";
	}

	@Test
	void EmployeeServiceNotNull(){
		assertNotNull(employeeService);
	}

	@Test
	void should_ReturnTru_When_EmailAndPasswordMatch() {
		//given
		this.employee =  new AdministratorAccountFactoryImpl().createTeacher(address,fullName,phone,password,email);

		//when
		when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.of( employee));
		when(passwordManager.matches(anyString(),anyString())).thenReturn(true);

		//then
		assertTrue(employeeService.signIn("test@test.com", "newPassword"));
	}

	@Test
	void updatePersonalData() {
	}

	@Test
	void updateEmail() {
	}

	@Test
	void updatePassword() {
	}

	@Test
	void getIdGrups() {
	}
}