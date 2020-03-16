package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.mapper.EmployeeMapper;
import pl.edu.pja.prz.account.mapper.EmployeeMapperImpl;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.service.EmployeeService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeFacadeImplTest {

    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeService employeeService;
    private EmployeeFacade employeeFacade;

    private AccountDto accountDto;

    @BeforeEach
    public void setUp() {
        employeeMapper = new EmployeeMapperImpl();

        employeeFacade = new EmployeeFacadeImpl(employeeMapper, employeeService);

        accountDto = new AccountDto();
        accountDto.setPhone("Phone");
        accountDto.setName("Name");
        accountDto.setSurname("Surname");
        accountDto.setPostalCode("PostalCode");
        accountDto.setCity("City");
        accountDto.setStreetNumber("Street Number");
        accountDto.setEmail("Email");
    }

    @Test
    public void Should_CreateEmployee() {
        //Given

        //When
        employeeFacade.createEmployee(accountDto);

        //Then
        verify(employeeService, only()).createEmployeeAccount(any(Person.class), anyString());
    }

    @Test
    public void Should_CreateAdministratorAccount() {
        //Given

        //When
        employeeFacade.createAdministratorAccount(accountDto);

        //Then
        verify(employeeService, only()).createAdministratorAccount(any(Person.class), anyString());
    }

    @Test
    public void Should_FindById() {
        //Given

        //When
        employeeFacade.findById(UUID.randomUUID());

        //Then
        verify(employeeService, only()).getById(any(UUID.class));
    }
}
