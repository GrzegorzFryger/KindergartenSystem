package pl.edu.pja.prz.calendar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.facade.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.facade.mapper.AbsenceMapper;
import pl.edu.pja.prz.calendar.service.AbsenceService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AbsenceFacadeImpl implements AbsenceFacade {
	private final AbsenceService absenceService;
	private final AbsenceMapper absenceMapper;

	@Autowired
	public AbsenceFacadeImpl(AbsenceService absenceService, AbsenceMapper absenceMapper) {
		this.absenceService = absenceService;
		this.absenceMapper = absenceMapper;
	}

	@Override
	public AbsenceDto createAbsence(AbsenceDto absenceDto) {
		return absenceMapper.fromAbsence(
				absenceService.createAbsence(
						absenceMapper.toAbsence(absenceDto)
				)
		);
	}

	@Override
	public AbsenceDto updateAbsence(AbsenceDto absenceDto) {
		return absenceMapper.fromAbsence(
				absenceService.updateAbsence(
						absenceMapper.toAbsence(absenceDto)
				)
		);
	}

	@Override
	public void deleteAbsence(Long id) {
		absenceService.deleteAbsence(id);
	}

	@Override
	public AbsenceDto getAbsence(Long id) {
		return absenceMapper.fromAbsence(
				absenceService.getAbsence(id)
		);
	}

	@Override
	public List<AbsenceDto> getAllAbsencesByDate(LocalDate date) {
		return null;
	}

	@Override
	public List<AbsenceDto> getAllAbsencesByChildId(UUID id) {
		return null;
	}
}
