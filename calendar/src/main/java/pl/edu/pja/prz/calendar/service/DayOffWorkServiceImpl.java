package pl.edu.pja.prz.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.calendar.model.DayOffWork;
import pl.edu.pja.prz.calendar.repository.DayOffWorkRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

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
}
