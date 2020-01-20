package pl.edu.pja.prz.account.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;

@Mapper(componentModel = "spring")
public interface ChildMapper {
	FullName toFullName(ChildDto childDto);

	Address toAddress(ChildDto childDto);

	Age toAge(ChildDto childDto);

	@Mapping(source = "startDate", target = "additionDate")
	@Mapping(source = "endDate", target = "endingDate")
	StudyPeriod toStudyPeriod(ChildDto childDto);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "fullName.name", target = "name")
	@Mapping(source = "fullName.surname", target = "surname")
	@Mapping(source = "address.postalCode", target = "postalCode")
	@Mapping(source = "address.city", target = "city")
	@Mapping(source = "address.streetNumber", target = "streetNumber")
	@Mapping(source = "peselNumber", target = "pesel")
	@Mapping(source = "studyPeriod.additionDate", target = "startDate")
	@Mapping(source = "studyPeriod.endingDate", target = "endDate")
	@Mapping(source = "borough.id", target = "boroughId")
	@Mapping(source = "age.dateOfBirth", target = "dateOfBirth")
	ChildDto fromChild(Child child);

	@InheritInverseConfiguration
	Child toChild(ChildDto childDto);
}