package pl.edu.pja.prz.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.model.Absence;
import pl.edu.pja.prz.calendar.repository.AbsenceRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AbsenceServiceImpl implements AbsenceService {
	private static final String ABSENCE = "Absence";

	private final AbsenceRepository absenceRepository;

	@Autowired
	public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
		this.absenceRepository = absenceRepository;
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
}
