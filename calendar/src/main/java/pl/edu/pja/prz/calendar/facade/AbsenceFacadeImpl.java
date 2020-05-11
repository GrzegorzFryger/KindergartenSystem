package pl.edu.pja.prz.calendar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.calendar.facade.mapper.AbsenceMapper;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.dto.AbsenceRangeDto;
import pl.edu.pja.prz.calendar.service.AbsenceService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AbsenceFacadeImpl implements AbsenceFacade {
	private final AbsenceService absenceService;
	private final AbsenceMapper absenceMapper;
	private final ChildFacade childFacade;

	@Autowired
	public AbsenceFacadeImpl(AbsenceService absenceService, AbsenceMapper absenceMapper, ChildFacade childFacade) {
		this.absenceService = absenceService;
		this.absenceMapper = absenceMapper;
		this.childFacade = childFacade;
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
	public List<AbsenceDto> getAllAbsences() {
		List<AbsenceDto> absenceDtoList;
		absenceDtoList = absenceMapper.absenceListToDtoList(
				absenceService.getAllAbsences());
		for (AbsenceDto absenceDto : absenceDtoList) {
			ChildDto childDto = childFacade.findChildById(absenceDto.getChildId());
			absenceDto.setChildName(childDto.getName());
			absenceDto.setChildSurname(childDto.getSurname());
		}
		return absenceDtoList;
	}

	@Override
	public List<AbsenceDto> getAllAbsencesByDate(LocalDate date) {
		return absenceMapper.absenceListToDtoList(
				absenceService.getAllAbsencesByDate(date)
		);
	}

	@Override
	public List<AbsenceDto> getAllAbsencesByChildId(UUID id) {
		return absenceMapper.absenceListToDtoList(
				absenceService.getAllAbsencesByChildId(id)
		);
	}

	@Override
	public List<AbsenceDto> getAllAbsencesForChildBetweenDates(UUID id, LocalDate startDate, LocalDate endDate) {
		return absenceMapper.absenceListToDtoList(
				absenceService.getAllAbsencesForChildBetweenDates(id, startDate, endDate)
		);
	}

	@Override
	public List<AbsenceDto> getAllAbsencesBetweenDates(LocalDate startDate, LocalDate endDate) {
		List<AbsenceDto> absenceDtoList;
		absenceDtoList = absenceMapper.absenceListToDtoList(
				absenceService.getAllAbsencesBetweenDates(startDate, endDate));
		for (AbsenceDto absenceDto : absenceDtoList) {
			ChildDto childDto = childFacade.findChildById(absenceDto.getChildId());
			absenceDto.setChildName(childDto.getName());
			absenceDto.setChildSurname(childDto.getSurname());
		}
		return absenceDtoList;
	}

	@Override
	public List<AbsenceDto> createAbsencesForChildBetweenDates(AbsenceRangeDto absenceRangeDto) {
		return absenceMapper.absenceListToDtoList(
				absenceService.createAbsencesForChildBetweenDates(
						absenceRangeDto.getChildId(),
						absenceRangeDto.getDateFrom(),
						absenceRangeDto.getDateTo(),
						absenceRangeDto.getReason()
				)
		);
	}
}
