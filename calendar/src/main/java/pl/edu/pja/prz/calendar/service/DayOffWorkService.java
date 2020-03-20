package pl.edu.pja.prz.calendar.service;

import pl.edu.pja.prz.calendar.model.DayOffWork;

import java.time.LocalDate;
import java.util.List;

public interface DayOffWorkService {

	DayOffWork createDayOffWork(DayOffWork dayOffWork);

	DayOffWork updateDayOffWork(DayOffWork dayOffWork);

	void deleteDayOffWork(Long id);

	DayOffWork getDayOffWork(Long id);

	List<DayOffWork> getAllDaysOff();

	List<DayOffWork> createDaysOffOnWeekends(int year);
}
