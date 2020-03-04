package pl.edu.pja.prz.calendar.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;
import pl.edu.pja.prz.calendar.model.DayOffWork;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DayOffWorkMapper {
	DayOffWork toDayOffWork(DayOffWorkDto dayOffWorkDto);

	@InheritInverseConfiguration
	DayOffWorkDto fromDayOffWork(DayOffWork dayOffWork);

	List<DayOffWorkDto> daysOffToDtos(List<DayOffWork> daysOff);


}