package pl.edu.pja.prz.account.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.facade.dto.PersonDto;
import pl.edu.pja.prz.account.model.Person;

@Mapper(componentModel ="spring")
public interface PersonMapper {
	@Mapping(source="address.postalCode", target="postalCode")
	@Mapping(source="address.city", target="city")
	@Mapping(source="address.streetNumber", target="streetNumber")
	@Mapping(source="fullName.name", target="name")
	@Mapping(source="fullName.surname", target="surname")
	@Mapping(source="phoneNumber.phone", target="phone")
	PersonDto fromPerson(Person person);

	@InheritInverseConfiguration
	Person toPerson(PersonDto personDto);
}
