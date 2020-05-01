package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Group;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.EmployeeRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.AccountFactoryImpl;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.mail.facade.MailFacade;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    PasswordManager passwordManager;
    private Address address;
    private Phone phone;
    private FullName fullName;
    private Password password;
    private String email;
    private Employee employee;
    private Person person;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private AccountFactory accountFactory;
    @Mock
    private RoleService roleService;
    @Mock
    MailFacade mailFacade;
    @Mock
    ActivateTokenService activateTokenService;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        address = new Address("70-700", "City", "Street 256");
        phone = new Phone("123132123");
        fullName = new FullName("TestName", "TestSurname");
        password = new Password("newPassword");
        email = "test@test.com";

        employeeService = new EmployeeService(employeeRepository, accountFactory, passwordManager, roleService,
                mailFacade,activateTokenService);
        this.employee = new AccountFactoryImpl().createTeacher(new Person(address, fullName, phone), password, email);
        this.person = new Person(address, fullName, phone);
    }

    @Test
    void EmployeeServiceNotNull() {
        assertNotNull(employeeService);
    }

    @Test
    void createEmployeeAccount() {
        //given
        var employee = new AccountFactoryImpl().createTeacher(new Person(address, fullName, phone), password, email);

        //when
        when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
        when(accountFactory.createStandardAccount(
                any(Person.class), any(Password.class), any(), any()
        )).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        doNothing().when(roleService).persistRoleFromUser(any(Employee.class));
        when(activateTokenService.generateToken(any(), any())).thenReturn("token");
        doNothing().when(mailFacade).sendEmail(any());

        var createdGuardian = employeeService.createEmployeeAccount(person, email);

        //then
        assertEquals(employee, createdGuardian);
        verify(passwordManager, times(1)).generateEncodeRandomPassword();
        verify(accountFactory, times(1))
                .createStandardAccount(
                        any(Person.class), any(Password.class), any(), any()
                );
        verify(employeeRepository, times(1)).save(any());
        verify(roleService, times(1)).persistRoleFromUser(any());

    }

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
    void shouldGetListGorps() {
        //given
        employee.setGroups(Set.of(new Group(1L, "test group"),
                new Group(2L, "test group")));
        //when
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        var groups = employeeService.getIdGroups(new UUID(1, 2));
        //then

        assertEquals(employee.getGroups(), groups);
    }
}