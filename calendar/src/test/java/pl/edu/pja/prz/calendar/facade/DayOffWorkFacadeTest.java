package pl.edu.pja.prz.calendar.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.facade.mapper.DayOffWorkMapper;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;
import pl.edu.pja.prz.calendar.service.DayOffWorkService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DayOffWorkFacadeTest {
	private Long id = 1L;

	@Mock
	private DayOffWorkService dayOffWorkService;

	@Mock
	private DayOffWorkMapper dayOffWorkMapper;

	private DayOffWorkFacade dayOffWorkFacade;

	private DayOffWorkDto dayOffWorkDto;

	@BeforeEach
	public void setUp() {
		dayOffWorkFacade = new DayOffWorkFacadeImpl(dayOffWorkService, dayOffWorkMapper);

		dayOffWorkDto = new DayOffWorkDto();
	}

	@Test
	public void shouldCreateDayOffWork() {
		//Given

		//When
		dayOffWorkFacade.createDayOffWork(dayOffWorkDto);

		//Then
		verify(dayOffWorkService, times(1)).createDayOffWork(
				dayOffWorkMapper.toDayOffWork(dayOffWorkDto));
	}

	@Test
	public void shouldUpdateDayOffWork() {
		//Given

		//When
		dayOffWorkFacade.updateDayOffWork(dayOffWorkDto);

		//Then
		verify(dayOffWorkService, times(1)).updateDayOffWork(
				dayOffWorkMapper.toDayOffWork(dayOffWorkDto));
	}

	@Test
	public void shouldDeleteDayOffWork() {
		//Given

		//When
		dayOffWorkFacade.deleteDayOffWork(id);

		//Then
		verify(dayOffWorkService, times(1)).deleteDayOffWork(id);
	}

	@Test
	public void shouldGetDayOffWorkById() {
		//Given

		//When
		dayOffWorkFacade.getDayOffWork(id);

		//Then
		verify(dayOffWorkService, times(1)).getDayOffWork(id);
	}

	@Test
	public void shouldCreateDaysOffOnWeekends() {
		//Given
		int year = 2020;

		//When
		dayOffWorkFacade.createDaysOffOnWeekends(year);

		//Then
		verify(dayOffWorkService, times(1)).createDaysOffOnWeekends(year);
	}
}
