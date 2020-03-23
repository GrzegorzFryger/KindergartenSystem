package pl.edu.pja.prz.calendar.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;
import pl.edu.pja.prz.calendar.facade.mapper.DayOffWorkMapper;
import pl.edu.pja.prz.calendar.service.DayOffWorkService;

import java.util.List;

@Service
public class DayOffWorkFacadeImpl implements DayOffWorkFacade {
	private final DayOffWorkService dayOffWorkService;
	private final DayOffWorkMapper dayOffWorkMapper;

	@Autowired
	public DayOffWorkFacadeImpl(DayOffWorkService dayOffWorkService, DayOffWorkMapper dayOffWorkMapper) {
		this.dayOffWorkService = dayOffWorkService;
		this.dayOffWorkMapper = dayOffWorkMapper;
	}

	@Override
	public DayOffWorkDto createDayOffWork(DayOffWorkDto dayOffWorkDto) {
		return dayOffWorkMapper.fromDayOffWork(
				dayOffWorkService.createDayOffWork(
						dayOffWorkMapper.toDayOffWork(dayOffWorkDto)
				)
		);
	}

	@Override
	public DayOffWorkDto updateDayOffWork(DayOffWorkDto dayOffWorkDto) {
		return dayOffWorkMapper.fromDayOffWork(
				dayOffWorkService.updateDayOffWork(
						dayOffWorkMapper.toDayOffWork(dayOffWorkDto)
				)
		);
	}

	@Override
	public void deleteDayOffWork(Long id) {
		dayOffWorkService.deleteDayOffWork(id);
	}

	@Override
	public DayOffWorkDto getDayOffWork(Long id) {
		return dayOffWorkMapper.fromDayOffWork(
				dayOffWorkService.getDayOffWork(id)
		);
	}

	@Override
	public List<DayOffWorkDto> getAllDaysOff() {
		return dayOffWorkMapper.daysOffToDtos(
				dayOffWorkService.getAllDaysOff()
		);
	}

	@Override
	public List<DayOffWorkDto> createDaysOffOnWeekends(int year) {
		return dayOffWorkMapper.daysOffToDtos(
				dayOffWorkService.createDaysOffOnWeekends(year)
		);
	}
}
