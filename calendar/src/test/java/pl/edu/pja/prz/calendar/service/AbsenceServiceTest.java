package pl.edu.pja.prz.calendar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.model.Absence;
import pl.edu.pja.prz.calendar.repository.AbsenceRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbsenceServiceTest {
	private Absence absence;
	@Mock
	private AbsenceRepository repository;
	private AbsenceServiceImpl service;

	@BeforeEach
	public void setUp() {
		service = new AbsenceServiceImpl(repository);

		absence = new Absence();
		absence.setReason("Choroba");
		absence.setDate(LocalDate.of(2019, Month.APRIL, 14));
		absence.setId(1L);
	}

	@Test
	public void ShouldCreateNewAbsence() {
		//Given

		//When
		service.createAbsence(absence);

		//Then
		verify(repository, times(1)).save(any(Absence.class));
	}

	@Test
	public void ShouldGetAbsence() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(absence));
		Long id = 1L;

		//When
		service.getAbsence(id);

		//Then
		verify(repository, times(1)).findById(id);
	}

	@Test
	public void ShouldThrowExceptionWhenAbsenceNotFound() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.getAbsence(1L);
		});

		//Then
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	public void ShouldUpdateAbsence() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(absence));

		//When
		service.updateAbsence(absence);

		//Then
		verify(repository, times(1)).save(any(Absence.class));

	}

	@Test
	public void ShouldDeleteAbsence() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(absence));

		//When
		service.deleteAbsence(1L);

		//Then
		verify(repository, times(1)).deleteById(anyLong());
	}
}
