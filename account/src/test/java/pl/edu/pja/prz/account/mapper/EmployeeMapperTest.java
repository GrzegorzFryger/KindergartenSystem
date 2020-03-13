package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.enums.EmployeeType;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {
	private EmployeeMapper employeeMapper;

	private AccountDto accountDto;
	private Employee employee;
	private EmployeeDto employeeDto;
	private Person person;

	private FullName fullName;
	private Address address;
	private Phone phone;
	private String email;
	private Password password;
	private AccountStatus status;
	private EmployeeType type;

	@BeforeEach
	public void setUp() {
		this.employeeMapper = Mappers.getMapper(EmployeeMapper.class);

		accountDto = new AccountDto();

		fullName = new FullName("TestName", "TestSurname");
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		email = "test@test.com";
		password = new Password("Password123");
		type = EmployeeType.TEACHER;
		status = AccountStatus.ACTIVE;


		employee = new Employee(address, fullName, phone, password, email, type);
		employeeDto = new EmployeeDto();
		person = new Person();

		accountDto.setName(fullName.getName());
		accountDto.setSurname(fullName.getSurname());
		accountDto.setPostalCode(address.getPostalCode());
		accountDto.setCity(address.getCity());
		accountDto.setStreetNumber(address.getStreetNumber());
		accountDto.setPhone(phone.getPhone());
		accountDto.setEmail(email);
		accountDto.setStatus(status);

		person.setFullName(fullName);
		person.setAddress(address);
		person.setPhoneNumber(phone);

		employeeDto.setName(fullName.getName());
		employeeDto.setSurname(fullName.getSurname());
		employeeDto.setPostalCode(address.getPostalCode());
		employeeDto.setCity(address.getCity());
		employeeDto.setStreetNumber(address.getStreetNumber());
		employeeDto.setPhone(phone.getPhone());
		employeeDto.setStatus(status);
		employeeDto.setEmail(email);
		employeeDto.setEmployeeType(type);
	}

	@Test
	public void Should_MapFromEmployee() {

		//When
		EmployeeDto newEmployeeDto = employeeMapper.fromEmployee(employee);

		//Then
		verifyDto(newEmployeeDto);

	}

	@Test
	public void Should_MapToPerson() {

		//When
		Person newPerson = employeeMapper.toPerson(accountDto);

		//Then
		verifyPerson(newPerson);
	}

	private void verifyDto(EmployeeDto employeeDto) {
		assertNotNull(employeeDto);
		assertEquals("TestName", employeeDto.getName());
		assertEquals("TestSurname", employeeDto.getSurname());
		assertEquals("70-700", employeeDto.getPostalCode());
		assertEquals("City", employeeDto.getCity());
		assertEquals("Street 256", employeeDto.getStreetNumber());
		assertEquals("123132123", employeeDto.getPhone());
		assertEquals("test@test.com", employeeDto.getEmail());
	}

	private void verifyPerson(Person person) {
		assertNotNull(person);
		assertEquals(fullName, person.getFullName());
		assertEquals(address, person.getAddress());
		assertEquals(phone, person.getPhoneNumber());
	}
}
