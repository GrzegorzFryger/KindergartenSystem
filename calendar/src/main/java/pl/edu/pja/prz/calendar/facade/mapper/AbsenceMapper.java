package pl.edu.pja.prz.calendar.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.calendar.facade.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.Absence;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {
	Absence toAbsence(AbsenceDto absenceDto);

	@InheritInverseConfiguration
	AbsenceDto fromAbsence(Absence absence);
}