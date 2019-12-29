package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.PersonDto;
import pl.edu.pja.prz.account.model.Person;

@Service
public interface PersonMapper {
	PersonDto fromPerson(Person person);
	Person toPerson(PersonDto personDto);
}
