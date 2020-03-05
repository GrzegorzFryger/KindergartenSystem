package pl.edu.pja.prz.calendar.facade.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;
import pl.edu.pja.prz.calendar.model.DayOffWork;
import pl.edu.pja.prz.calendar.model.enums.EventType;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DayOffWorkMapperTest {
	private DayOffWorkMapper dayOffWorkMapper;

	private DayOffWork dayOffWork;
	private DayOffWorkDto dayOffWorkDto;

	private LocalDate date;
	private String name;
	private EventType eventType;
	private Long id;

	@BeforeEach
	public void setUp() {
		this.dayOffWorkMapper = Mappers.getMapper(DayOffWorkMapper.class);

		dayOffWork = new DayOffWork();
		dayOffWorkDto = new DayOffWorkDto();

		date = LocalDate.of(2019, Month.APRIL, 14);
		name = "Jakies swieto";
		eventType = EventType.HOLIDAY;
		id = 1L;

		dayOffWork.setId(id);
		dayOffWork.setDate(date);
		dayOffWork.setName(name);
		dayOffWork.setEventType(eventType);

		dayOffWorkDto.setId(id);
		dayOffWorkDto.setDate(date);
		dayOffWorkDto.setName(name);
		dayOffWorkDto.setEventType(eventType);
	}

	@Test
	public void ShouldMapToDto() {
		//Given

		//When
		DayOffWorkDto newDayOffWorkDto = dayOffWorkMapper.fromDayOffWork(dayOffWork);

		//Then
		verifyDayOffWorkDto(newDayOffWorkDto);
	}

	@Test
	public void ShouldMapFromDto() {
		//Given

		//When
		DayOffWork newDayOffWork = dayOffWorkMapper.toDayOffWork(dayOffWorkDto);

		//Then
		verifyDayOffWork(newDayOffWork);
	}

	private void verifyDayOffWorkDto(DayOffWorkDto dayOffWorkDto) {
		assertNotNull(dayOffWorkDto);
		assertEquals(name, dayOffWorkDto.getName());
		assertEquals(date, dayOffWorkDto.getDate());
		assertEquals(id, dayOffWorkDto.getId());
		assertEquals(eventType, dayOffWorkDto.getEventType());
	}

	private void verifyDayOffWork(DayOffWork dayOffWork) {
		assertNotNull(dayOffWork);
		assertEquals(name, dayOffWork.getName());
		assertEquals(date, dayOffWork.getDate());
		assertEquals(id, dayOffWork.getId());
		assertEquals(eventType, dayOffWork.getEventType());
	}

}
