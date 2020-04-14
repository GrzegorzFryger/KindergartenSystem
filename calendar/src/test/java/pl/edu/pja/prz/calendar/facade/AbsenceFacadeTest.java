package pl.edu.pja.prz.calendar.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.facade.mapper.AbsenceMapper;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.dto.AbsenceRangeDto;
import pl.edu.pja.prz.calendar.service.AbsenceService;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbsenceFacadeTest {
	private Long id = 1L;
	@Mock
	private AbsenceService absenceService;

	@Mock
	private AbsenceMapper absenceMapper;

	private AbsenceFacade absenceFacade;

	private AbsenceDto absenceDto;

	@BeforeEach
	public void setUp() {
		absenceFacade = new AbsenceFacadeImpl(absenceService, absenceMapper);

		absenceDto = new AbsenceDto();
	}

	@Test
	public void shouldCreateAbsence() {
		//Given

		//When
		absenceFacade.createAbsence(absenceDto);

		//Then
		verify(absenceService, times(1)).createAbsence(absenceMapper.toAbsence(absenceDto));
	}

	@Test
	public void shouldUpdateAbsence() {
		//Given

		//When
		absenceFacade.updateAbsence(absenceDto);

		//Then
		verify(absenceService, times(1)).updateAbsence(absenceMapper.toAbsence(absenceDto));
	}

	@Test
	public void shouldDeleteAbsence() {
		//Given

		//When
		absenceFacade.deleteAbsence(id);

		//Then
		verify(absenceService, times(1)).deleteAbsence(id);
	}

	@Test
	public void shouldGetAbsenceById() {
		//Given

		//When
		absenceFacade.getAbsence(id);

		//Then
		verify(absenceService, times(1)).getAbsence(id);
	}

	@Test
	public void shouldGetAllAbsencesByChildId() {
		//Given
		var childId = UUID.randomUUID();

		//When
		absenceFacade.getAllAbsencesByChildId((childId));

		//Then
		verify(absenceService, times(1)).getAllAbsencesByChildId(childId);
	}

	@Test
	public void shouldGetAllAbsencesByDate() {
		//Given
		LocalDate date = LocalDate.of(2019, Month.FEBRUARY, 24);

		//When
		absenceFacade.getAllAbsencesByDate(date);

		//Then
		verify(absenceService, times(1)).getAllAbsencesByDate(date);
	}

	@Test
	public void shouldGetAllAbsencesForChildBetweenDates() {
		//Given
		var childId = UUID.randomUUID();
		LocalDate dateFrom = LocalDate.of(2019, Month.JANUARY, 1);
		LocalDate dateTo = LocalDate.of(2019, Month.APRIL, 14);

		//When
		absenceFacade.getAllAbsencesForChildBetweenDates(childId, dateFrom, dateTo);

		//Then
		verify(absenceService, times(1)).getAllAbsencesForChildBetweenDates(childId, dateFrom, dateTo);
	}

	@Test
	public void shouldCreateAbsenceForChildBetweenDates() {
		//Given
		var childId = UUID.randomUUID();
		LocalDate dateFrom = LocalDate.of(2019, Month.JANUARY, 1);
		LocalDate dateTo = LocalDate.of(2019, Month.APRIL, 14);
		String reason = "Reason";

		//When
		absenceFacade.createAbsencesForChildBetweenDates(new AbsenceRangeDto(childId, dateFrom, dateTo,reason));

		//Then
		verify(absenceService, times(1)).createAbsencesForChildBetweenDates(any(), any(), any(), any());
	}
}
