package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.BoroughRepository;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdministratorServiceTest {

	@Mock
	private AdministratorAccountFactoryImpl administratorAccountFactory;
	@Mock
	private BoroughRepository boroughRepository;
	@Mock
	private ChildService childService;
	@Mock
	private EmployeeRepository employeeRepository;
	@Mock
	private GuardianRepository guardianRepository;
	@Mock
	private PasswordManager passwordManager;
	@Mock
	private RoleService roleService;

	private AdministratorService administratorService;

	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;

	@BeforeEach
	void setUp() {
		administratorService = new AdministratorService(administratorAccountFactory, boroughRepository, childService,
				employeeRepository, guardianRepository, passwordManager, roleService);

		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";
	}

	@Test
	void createGuardianAccount() {
		//given
		var guardian = new AdministratorAccountFactoryImpl().createGuardian(new Person(address, fullName, phone), password, email);

		//when
		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
		when(administratorAccountFactory.createStandardAccount(
				any(Person.class), any(Password.class), any(), any()
		)).thenReturn(guardian);
		when(guardianRepository.save(any(Guardian.class))).thenReturn(guardian);
		doNothing().when(roleService).persistRoleFromUser(any(Guardian.class));
		var createdGuardian = administratorService.createGuardianAccount(address, fullName, phone, email);

		//then
		assertEquals(guardian, createdGuardian);
		verify(passwordManager, times(1)).generateEncodeRandomPassword();
		verify(administratorAccountFactory, times(1))
				.createStandardAccount(
						any(Person.class), any(Password.class), any(), any()
				);
		verify(guardianRepository, times(1)).save(any());
		verify(roleService, times(1)).persistRoleFromUser(any());

	}

	@Test
	void createEmployeeAccount() {
		//given
		var employee = new AdministratorAccountFactoryImpl().createTeacher(new Person(address, fullName, phone), password, email);

		//when
		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
		when(administratorAccountFactory.createStandardAccount(
				any(Person.class), any(Password.class), any(), any()
		)).thenReturn(employee);
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		doNothing().when(roleService).persistRoleFromUser(any(Employee.class));
		var createdGuardian = administratorService.createEmployeeAccount(address, fullName, phone, email);

		//then
		assertEquals(employee, createdGuardian);
		verify(passwordManager, times(1)).generateEncodeRandomPassword();
		verify(administratorAccountFactory, times(1))
				.createStandardAccount(
						any(Person.class), any(Password.class), any(), any()
				);
		verify(employeeRepository, times(1)).save(any());
		verify(roleService, times(1)).persistRoleFromUser(any());

	}

	@Test
	void createAdministratorAccount() {

		var administrator = new AdministratorAccountFactoryImpl().createAdministrator(new Person(address, fullName, phone),
				password, email);

		//when
		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
		when(administratorAccountFactory.createAdministrator(
				any(Person.class), any(Password.class), any()
		)).thenReturn(administrator);
		when(employeeRepository.save(any(Employee.class))).thenReturn(administrator);
		doNothing().when(roleService).persistRoleFromUser(any(Employee.class));
		var createdAdministrator = administratorService.createAdministratorAccount(address, fullName, phone, email);

		//then
		assertEquals(administrator, createdAdministrator);
		verify(passwordManager, times(1)).generateEncodeRandomPassword();
		verify(administratorAccountFactory, times(1))
				.createAdministrator(any(Person.class), any(Password.class), any());
		verify(employeeRepository, times(1)).save(any());
		verify(roleService, times(1)).persistRoleFromUser(any());
	}

	@Test
	void createChild() {
		//given
		var borough = new Borough("Testborought", address, phone, "test@wp.com", "975456466");
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withBorough(borough)
				.build();

		//when
		when(boroughRepository.findById(any())).thenReturn(Optional.of(borough));
		when(childService.createChild(any(), any(), any(), any(), any())).thenReturn(child);
		when(boroughRepository.save(any())).thenReturn(borough);
		var createdChild = administratorService.createChild(1L, address, fullName, "00440758725", new StudyPeriod());

		//then
		assertEquals(child, createdChild);
		verify(boroughRepository, times(1)).findById(any(Long.class));
		verify(childService, times(1)).createChild(any(Address.class), any(Borough.class),
				any(FullName.class), any(String.class), any(StudyPeriod.class)
		);
		verify(boroughRepository, times(1)).save(any(Borough.class));
	}


}