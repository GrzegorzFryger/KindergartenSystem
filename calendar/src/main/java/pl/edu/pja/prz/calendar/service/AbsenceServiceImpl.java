package pl.edu.pja.prz.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.model.Absence;
import pl.edu.pja.prz.calendar.repository.AbsenceRepository;
import pl.edu.pja.prz.calendar.repository.DayOffWorkRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AbsenceServiceImpl implements AbsenceService {
	private static final String ABSENCE = "Absence";

	private final AbsenceRepository absenceRepository;
	private final DayOffWorkRepository dayOffWorkRepository;

	@Autowired
	public AbsenceServiceImpl(AbsenceRepository absenceRepository, DayOffWorkRepository dayOffWorkRepository) {
		this.absenceRepository = absenceRepository;
		this.dayOffWorkRepository = dayOffWorkRepository;
	}

	@Override
	public Absence createAbsence(Absence absence) {
		return absenceRepository.save(absence);
	}

	@Override
	public Absence updateAbsence(Absence absence) {
		if (absenceRepository.findById(absence.getId()).isEmpty()) {
			throw new ElementNotFoundException(ABSENCE, absence.getId());
		}

		Absence absenceToUpdate = getAbsence(absence.getId());

		if (absenceToUpdate.getDate() != null) {
			absenceToUpdate.setDate(absence.getDate());
		}

		if (absenceToUpdate.getReason() != null) {
			absenceToUpdate.setReason(absence.getReason());
		}

		return absenceRepository.save(absenceToUpdate);
	}

	@Override
	public void deleteAbsence(Long id) {
		if (absenceRepository.findById(id).isEmpty()) {
			throw new ElementNotFoundException(ABSENCE, id);
		}
		absenceRepository.deleteById(id);

	}

	@Override
	public Absence getAbsence(Long id) {
		return absenceRepository.findById(id).orElseThrow(
				() -> new ElementNotFoundException(ABSENCE, id));
	}

	@Override
	public List<Absence> getAllAbsencesByDate(LocalDate date) {
		return absenceRepository.findAllByDate(date);
	}

	@Override
	public List<Absence> getAllAbsencesByChildId(UUID id) {
		return absenceRepository.findAllByChildId(id);
	}

	@Override
	public List<Absence> getAllAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate) {
		return absenceRepository.findAllByChildIdBetweenDates(id, startDate, endDate);
	}

	@Override
	public List<Absence> createAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate, String reason) {
		List<Absence> absenceList = new ArrayList<>();
		LocalDate endDatePlus = endDate.plusDays(1);
		for (LocalDate date = startDate; date.isBefore(endDatePlus); date = date.plusDays(1)) {
			Absence absence = new Absence();
			absence.setChildId(id);
			absence.setDate(date.plusDays(1));
			absence.setReason(reason);
			absenceList.add(absence);
		}
		return absenceRepository.saveAll(absenceList);
	}
}
