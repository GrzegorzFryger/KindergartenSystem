package pl.edu.pja.prz.calendar.facade;

import pl.edu.pja.prz.calendar.facade.dto.AbsenceDto;

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
}
