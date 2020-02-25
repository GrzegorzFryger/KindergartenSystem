package pl.edu.pja.prz.calendar.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.calendar.facade.dto.DayOffWorkDto;
import pl.edu.pja.prz.calendar.model.DayOffWork;

@Mapper(componentModel = "spring")
public interface DayOffWorkMapper {
	DayOffWork toDayOffWork(DayOffWorkDto dayOffWorkDto);

	@InheritInverseConfiguration
	DayOffWorkDto fromDayOffWork(DayOffWork dayOffWork);
}