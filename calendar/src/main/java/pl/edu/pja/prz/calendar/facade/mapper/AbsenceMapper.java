package pl.edu.pja.prz.calendar.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.Absence;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {
	Absence toAbsence(AbsenceDto absenceDto);

	@InheritInverseConfiguration
	AbsenceDto fromAbsence(Absence absence);

	List<AbsenceDto> absenceListToDtoList(List<Absence> absenceList);
}