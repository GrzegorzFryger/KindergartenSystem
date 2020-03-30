package pl.edu.pja.prz.calendar.facade;

import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.dto.AbsenceRangeDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AbsenceFacade {
	AbsenceDto createAbsence(AbsenceDto absenceDto);

	AbsenceDto updateAbsence(AbsenceDto absenceDto);

	void deleteAbsence(Long id);

	AbsenceDto getAbsence(Long id);

	List<AbsenceDto> getAllAbsencesByDate(LocalDate date);

	List<AbsenceDto> getAllAbsencesByChildId(UUID id);

	List<AbsenceDto> getAllAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate);

    List<AbsenceDto> createAbsencesForChildBetweenDates(AbsenceRangeDto absenceRangeDto);
}
