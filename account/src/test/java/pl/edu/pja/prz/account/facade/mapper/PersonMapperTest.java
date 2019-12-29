package pl.edu.pja.prz.account.facade.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.facade.dto.PersonDto;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonMapperTest {
	private UUID id;
	private Address address;
	private Phone phone;
	private FullName fullName;
	private PersonMapper personMapper;

	@BeforeEach
	void setUp() {
		id = UUID.randomUUID();
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		personMapper = new PersonMapperImpl();
	}

	@Test
	public void should_convertPersonToDto() {
		//given
		var person = new Person(address, fullName, phone);
		person.setId(id);

		var personDto = new PersonDto(id, fullName.getName(), fullName.getSurname(), address.getPostalCode(),
				address.getCity(), address.getStreetNumber(), phone.getPhone()
		);

		//when
		var personDtoFromMapper = personMapper.fromPerson(person);

		//then
		assertEquals(personDto,personDtoFromMapper);

	}

	@Test
	public void should_convertPersonDtoToPerson() {
		//given
		var personDto = new PersonDto(id, fullName.getName(), fullName.getSurname(), address.getPostalCode(),
				address.getCity(), address.getStreetNumber(), phone.getPhone()
		);
		var person = new Person(address,fullName,phone);
		person.setId(id);

		//when
		var personFromMapper = personMapper.toPerson(personDto);

		//then
		assertEquals(person,personFromMapper);
	}

}