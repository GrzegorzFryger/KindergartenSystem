package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

	private AccountMapper accountMapper;

	private Account account;
	private AccountDto accountDto;
	private FullName fullName;
	private Address address;
	private Phone phone;
	private String email;
	private AccountStatus status;
	private Person person;

	@BeforeEach
	public void setUp() {
		this.accountMapper = Mappers.getMapper( AccountMapper.class );
		account = new Account();
		accountDto = new AccountDto();
		person = new Person();
		fullName = new FullName("TestName", "TestSurname");
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		email = "test@test.com";
		status = AccountStatus.ACTIVE;

		account.setFullName(fullName);
		account.setAddress(address);
		account.setPhoneNumber(phone);
		account.setEmail(email);
		account.setAccountStatus(status);

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
	}

	@Test
	public void Should_MapAccount() {
		//When
		AccountDto newAccountDto = accountMapper.fromAccount(account);

		//Then
		verifyDto(newAccountDto);
	}

	@Test
	public void Should_MapFromAccount() {
		//When
		Account newAccount = accountMapper.toAccount(accountDto);

		//Then
		verifyFromDto(newAccount);
	}

	@Test
	public void Should_MapPerson() {
		//When
		Person newPerson = accountMapper.toPerson(accountDto);

		//Then
		verifyPerson(newPerson);
	}

	private void verifyDto(AccountDto accountDto) {
		assertNotNull(accountDto);
		assertEquals("TestName", accountDto.getName());
		assertEquals("TestSurname", accountDto.getSurname());
		assertEquals("70-700", accountDto.getPostalCode());
		assertEquals("City", accountDto.getCity());
		assertEquals("Street 256", accountDto.getStreetNumber());
		assertEquals("123132123", accountDto.getPhone());
		assertEquals("test@test.com", accountDto.getEmail());
		assertEquals(AccountStatus.ACTIVE, accountDto.getStatus());
	}

	private void verifyFromDto(Account account) {
		assertNotNull(account);
		assertEquals(fullName, account.getFullName());
		assertEquals(address, account.getAddress());
		assertEquals(phone, account.getPhoneNumber());
		assertEquals(email, account.getEmail());
		assertEquals(status, account.getAccountStatus());
	}

	private void verifyPerson(Person person) {
		assertNotNull(person);
		assertEquals(fullName, person.getFullName());
		assertEquals(address, person.getAddress());
		assertEquals(phone, person.getPhoneNumber());
	}
}
