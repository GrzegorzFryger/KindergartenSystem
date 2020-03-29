package pl.edu.pja.prz.calendar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.model.Absence;
import pl.edu.pja.prz.calendar.repository.AbsenceRepository;
import pl.edu.pja.prz.calendar.repository.DayOffWorkRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbsenceServiceTest {
	private Absence absence;
	private List<Absence> absenceList;
	@Mock
	private AbsenceRepository absenceRepository;
	@Mock
	private DayOffWorkRepository dayOffWorkRepository;
	private AbsenceServiceImpl service;

	@BeforeEach
	public void setUp() {
		service = new AbsenceServiceImpl(absenceRepository, dayOffWorkRepository);

		absence = new Absence();
		absence.setReason("Choroba");
		absence.setDate(LocalDate.of(2019, Month.APRIL, 14));
		absence.setId(1L);

		absenceList = new ArrayList<>();
		absenceList.add(absence);
	}

	@Test
	public void ShouldCreateNewAbsence() {
		//Given

		//When
		service.createAbsence(absence);

		//Then
		verify(absenceRepository, times(1)).save(any(Absence.class));
	}

	@Test
	public void ShouldGetAbsence() {
		//Given
		when(absenceRepository.findById(anyLong())).thenReturn(Optional.of(absence));
		Long id = 1L;

		//When
		service.getAbsence(id);

		//Then
		verify(absenceRepository, times(1)).findById(id);
	}

	@Test
	public void ShouldThrowExceptionWhenAbsenceNotFound() {
		//Given
		when(absenceRepository.findById(anyLong())).thenReturn(Optional.empty());

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.getAbsence(1L);
		});

		//Then
		verify(absenceRepository, times(1)).findById(anyLong());
	}

	@Test
	public void ShouldUpdateAbsence() {
		//Given
		when(absenceRepository.findById(anyLong())).thenReturn(Optional.of(absence));

		//When
		service.updateAbsence(absence);

		//Then
		verify(absenceRepository, times(1)).save(any(Absence.class));

	}

	@Test
	public void ShouldDeleteAbsence() {
		//Given
		when(absenceRepository.findById(anyLong())).thenReturn(Optional.of(absence));

		//When
		service.deleteAbsence(1L);

		//Then
		verify(absenceRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void ShouldGetAllAbsencesByDate() {
		//Given
		when(absenceRepository.findAllByDate(any(LocalDate.class))).thenReturn(absenceList);

		//When
		service.getAllAbsencesByDate(LocalDate.now());

		//Then
		verify(absenceRepository, times(1)).findAllByDate(any(LocalDate.class));
	}

	@Test
	public void ShouldGetAllAbsencesByChildId() {
		//Given
		when(absenceRepository.findAllByChildId(any(UUID.class))).thenReturn(absenceList);

		//When
		service.getAllAbsencesByChildId(UUID.randomUUID());

		//Then
		verify(absenceRepository, times(1)).findAllByChildId(any(UUID.class));
	}

	@Test
	public void ShouldGetAllAbsencesForChildBetweenDates() {
		//Given
		when(absenceRepository.findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class),
				any(LocalDate.class))).thenReturn(absenceList);

		//When
		service.getAllAbsencesForChildBetweenDates(UUID.randomUUID(), LocalDate.now(), LocalDate.now());

		//Then
		verify(absenceRepository, times(1)).findAllByChildIdBetweenDates(any(UUID.class),
				any(LocalDate.class), any(LocalDate.class));
	}
}
