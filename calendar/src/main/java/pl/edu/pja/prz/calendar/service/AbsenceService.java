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

	List<Absence> getAllAbsencesByDate(LocalDate date);

	List<Absence> getAllAbsencesByChildId(UUID id);
}
