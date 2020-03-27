package pl.edu.pja.prz.calendar.service;

import pl.edu.pja.prz.calendar.model.DayOffWork;

import java.util.List;

public interface DayOffWorkService {

	DayOffWork createDayOffWork(DayOffWork dayOffWork);

	DayOffWork updateDayOffWork(DayOffWork dayOffWork);

	void deleteDayOffWork(Long id);

	DayOffWork getDayOffWork(Long id);

	List<DayOffWork> getAllDaysOff();

	boolean isTodayDayOff();

	List<DayOffWork> createDaysOffOnWeekends(int year);
}
