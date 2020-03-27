package pl.edu.pja.prz.calendar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.model.DayOffWork;
import pl.edu.pja.prz.calendar.model.enums.EventType;
import pl.edu.pja.prz.calendar.repository.DayOffWorkRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DayOffWorkServiceTest {
	private DayOffWork dayOffWork;
	@Mock
	private DayOffWorkRepository repository;
	private DayOffWorkServiceImpl service;

	@BeforeEach
	public void setUp() {
		service = new DayOffWorkServiceImpl(repository);

		dayOffWork = new DayOffWork();

		dayOffWork.setEventType(EventType.HOLIDAY);
		dayOffWork.setName("Swieto Lasu");
		dayOffWork.setDate(LocalDate.of(2019, Month.APRIL, 14));
		dayOffWork.setId(1L);
	}

	@Test
	public void ShouldCreateNewDayOff() {
		//Given

		//When
		service.createDayOffWork(dayOffWork);

		//Then
		verify(repository, times(1)).save(any(DayOffWork.class));
	}

	@Test
	public void ShouldGetDayOff() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(dayOffWork));
		Long id = 1L;

		//When
		service.getDayOffWork(id);

		//Then
		verify(repository, times(1)).findById(id);
	}

	@Test
	public void ShouldGetAllDaysOff() {
		//Given
		List<DayOffWork> dayOffWorkList = new ArrayList<>();
		dayOffWorkList.add(dayOffWork);
		when(repository.findAll()).thenReturn(dayOffWorkList);

		//When
		service.getAllDaysOff();

		//Then
		verify(repository, times(1)).findAll();
	}

	@Test
	public void ShouldThrowExceptionWhenDayOffNotFound() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.getDayOffWork(1L);
		});

		//Then
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	public void ShouldUpdateDayOff() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(dayOffWork));

		//When
		service.updateDayOffWork(dayOffWork);

		//Then
		verify(repository, times(1)).save(any(DayOffWork.class));
	}

	@Test
	public void ShouldDeleteDayOff() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(dayOffWork));

		//When
		service.deleteDayOffWork(1L);

		//Then
		verify(repository, times(1)).deleteById(anyLong());
	}

	@Test
	public void ShouldCreateListOfDaysOffOnWeekends() {
		//Given
		int year = 2020;

		//When
		service.createDaysOffOnWeekends(year);

		//Then
		verify(repository, times(1)).saveAll(anyIterable());
	}

    @Test
    void ShouldTodayDayOff_returnTrue() {
		//Given
		when(repository.findDayOffWorkByDate(LocalDate.now())).thenReturn(Optional.of(new DayOffWork()));
		//When
		boolean isTodayOff = service.isTodayDayOff();
		//Then
		Assertions.assertTrue(isTodayOff);
    }
}
