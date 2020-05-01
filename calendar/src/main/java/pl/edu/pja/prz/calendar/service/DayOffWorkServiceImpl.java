package pl.edu.pja.prz.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.model.DayOffWork;
import pl.edu.pja.prz.calendar.model.enums.EventType;
import pl.edu.pja.prz.calendar.repository.DayOffWorkRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DayOffWorkServiceImpl implements DayOffWorkService {
	private static final String DAYOFF = "Day off";

	private final DayOffWorkRepository dayOffWorkRepository;

	@Autowired
	public DayOffWorkServiceImpl(DayOffWorkRepository dayOffWorkRepository) {
		this.dayOffWorkRepository = dayOffWorkRepository;
	}

	@Override
	public DayOffWork createDayOffWork(DayOffWork dayOffWork) {
		return dayOffWorkRepository.save(dayOffWork);
	}

	@Override
	public DayOffWork updateDayOffWork(DayOffWork dayOffWork) {
		if (dayOffWorkRepository.findById(dayOffWork.getId()).isEmpty()) {
			throw new ElementNotFoundException(DAYOFF, dayOffWork.getId());
		}

		DayOffWork dayOffToUpdate = getDayOffWork(dayOffWork.getId());

		if (dayOffToUpdate.getDate() != null) {
			dayOffToUpdate.setDate(dayOffWork.getDate());
		}

		if (dayOffToUpdate.getName() != null) {
			dayOffToUpdate.setName(dayOffWork.getName());
		}

		if (dayOffToUpdate.getEventType() != null) {
			dayOffToUpdate.setEventType(dayOffWork.getEventType());
		}

		return dayOffWorkRepository.save(dayOffToUpdate);
	}

	@Override
	public void deleteDayOffWork(Long id) {
		if (dayOffWorkRepository.findById(id).isEmpty()) {
			throw new ElementNotFoundException(DAYOFF, id);
		}
		dayOffWorkRepository.deleteById(id);

	}

	@Override
	public DayOffWork getDayOffWork(Long id) {
		return dayOffWorkRepository.findById(id).orElseThrow(
				() -> new ElementNotFoundException(DAYOFF, id));
	}

	@Override
	public List<DayOffWork> getAllDaysOff() {
		return dayOffWorkRepository.findAll();
	}

	@Override
	public boolean isTodayDayOff() {
		return dayOffWorkRepository.findDayOffWorkByDate(LocalDate.now()).isPresent();
	}

	@Override
	public List<DayOffWork> createDaysOffOnWeekends(int year) {
		List<LocalDate> weekendDateList = findWeekends(year);
		List<DayOffWork> weekendsOff = new ArrayList<>();
		for (LocalDate date : weekendDateList
		) {
			DayOffWork dayOff = new DayOffWork();
			dayOff.setDate(date);
			dayOff.setEventType(EventType.WEEKEND);
			dayOff.setName("Weekend");
			if (dayOffWorkRepository.findDayOffWorkByDate(date).isEmpty()) {
				weekendsOff.add(dayOff);
			}
		}
		return dayOffWorkRepository.saveAll(weekendsOff);
	}

	private List<LocalDate> findWeekends(int year) {
		List<LocalDate> weekendList = new ArrayList<>();
		LocalDate startDate = LocalDate.of(year, Month.JANUARY, 1);
		LocalDate endDate = LocalDate.of(year, Month.DECEMBER, 31);
		LocalDate next = startDate.minusDays(1);

		while ((next = next.plusDays(1)).isBefore(endDate.plusDays(1))) {
			if (next.getDayOfWeek() == DayOfWeek.SATURDAY || next.getDayOfWeek() == DayOfWeek.SUNDAY) {
				weekendList.add(next);
			}
		}
		return weekendList;
	}
}
