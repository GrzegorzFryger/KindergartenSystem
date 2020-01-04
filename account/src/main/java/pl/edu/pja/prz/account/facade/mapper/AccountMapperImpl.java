package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;

@Component
public class AccountMapperImpl implements AccountMapper {

	@Override
	public AccountDto fromAccount(Account account) {
		if (account == null) {
			return null;
		}

		var accountDto = new AccountDto();
		accountDto.setPostalCode(account.getAddress().getPostalCode());
		accountDto.setCity(account.getAddress().getCity());
		accountDto.setStreetNumber(account.getAddress().getStreetNumber());


		accountDto.setName(account.getFullName().getName());
		accountDto.setSurname(account.getFullName().getSurname());

		accountDto.setEmail(account.getEmail());
		accountDto.setPhone(account.getPhoneNumber().getPhone());
		accountDto.setStatus(account.getAccountStatus());

		return accountDto;
	}

	@Override
	public Account toAccount(AccountDto accountDto) {
		if (accountDto == null) {
			return null;
		}

		var account = new Account();

		setAddress(accountDto, account);
		setFullName(accountDto, account);
		setPhone(accountDto, account);

		account.setEmail(accountDto.getEmail());
		account.setAccountStatus(accountDto.getStatus());

		return account;
	}

	@Override
	public Person toPerson(AccountDto accountDto) {
		if (accountDto == null) {
			return null;
		}

		var person = new Person();

		setAddress(accountDto, person);
		setFullName(accountDto, person);
		setPhone(accountDto, person);

		person.setFullName(new FullName(accountDto.getName(), accountDto.getSurname()));
		person.setPhoneNumber(new Phone(accountDto.getPhone()));

		return person;
	}

	private <T extends Person> void setAddress(AccountDto accountDto, T person) {
		person.setAddress(new Address(accountDto.getPostalCode(), accountDto.getCity(), accountDto.getStreetNumber()));

	}

	private <T extends Person> void setFullName(AccountDto accountDto, T person) {
		person.setAddress(new Address(accountDto.getPostalCode(), accountDto.getCity(), accountDto.getStreetNumber()));

	}

	private <T extends Person> void setPhone(AccountDto accountDto, T person) {
		person.setAddress(new Address(accountDto.getPostalCode(), accountDto.getCity(), accountDto.getStreetNumber()));

	}
}
