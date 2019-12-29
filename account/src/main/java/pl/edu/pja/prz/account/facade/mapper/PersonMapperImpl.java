package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.PersonDto;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Phone;

@Component
public class PersonMapperImpl implements PersonMapper {

	@Override
	public PersonDto fromPerson(Person person) {
		if (person == null) {
			return null;
		}

		PersonDto personDto = new PersonDto();

		personDto.setCity(person.getAddress().getCity());
		personDto.setStreetNumber(person.getAddress().getStreetNumber());
		personDto.setPhone(person.getPhoneNumber().getPhone());
		personDto.setSurname(person.getFullName().getSurname());
		personDto.setName(person.getFullName().getName());
		personDto.setPostalCode(person.getAddress().getPostalCode());
		personDto.setId(person.getId());

		return personDto;
	}

	@Override
	public Person toPerson(PersonDto personDto) {
		if (personDto == null) {
			return null;
		}

		Person person = new Person();

		person.setAddress(new Address(personDto.getPostalCode(), personDto.getCity(), personDto.getStreetNumber()));
		person.setFullName(new FullName(personDto.getName(), personDto.getSurname()));
		person.setPhoneNumber(new Phone(personDto.getPhone()));
		person.setId(personDto.getId());

		return person;
	}


}
