package pl.edu.pja.prz.calendar.service;

import pl.edu.pja.prz.calendar.model.Absence;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AbsenceService {

	Absence createAbsence(Absence absence);

	Absence updateAbsence(Absence absence);

	void deleteAbsence(Long id);

	Absence getAbsence(Long id);

	List<Absence> getAllAbsences();

	List<Absence> getAllAbsencesByDate(LocalDate date);

	List<Absence> getAllAbsencesByChildId(UUID id);

	List<Absence> getAllAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate);

	List<Absence> getAllAbsencesBetweenDates(LocalDate startDate, LocalDate endDate);

	List<Absence> createAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate, String reason);
}
