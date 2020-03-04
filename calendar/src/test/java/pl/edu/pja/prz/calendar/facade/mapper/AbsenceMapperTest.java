package pl.edu.pja.prz.calendar.facade.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.Absence;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AbsenceMapperTest {
	private AbsenceMapper absenceMapper;

	private Absence absence;
	private AbsenceDto absenceDto;

	private LocalDate date;
	private String reason;
	private UUID childId;
	private Long id;

	@BeforeEach
	public void setUp() {
		this.absenceMapper = Mappers.getMapper(AbsenceMapper.class);

		absence = new Absence();
		absenceDto = new AbsenceDto();

		date = LocalDate.of(2019, Month.APRIL, 14);
		reason = "Choroba";
		childId = UUID.fromString("d55bd740-f039-4d18-87fc-11d6f73376b5");
		id = 1L;

		absence.setId(id);
		absence.setDate(date);
		absence.setReason(reason);
		absence.setChildId(childId);

		absenceDto.setId(id);
		absenceDto.setDate(date);
		absenceDto.setReason(reason);
		absenceDto.setChildId(childId);
	}

	@Test
	public void ShouldMapToDto() {
		//Given

		//When
		AbsenceDto newAbsenceDto = absenceMapper.fromAbsence(absence);

		//Then
		verifyAbsenceDto(newAbsenceDto);
	}

	@Test
	public void ShouldMapFromDto() {
		//Given

		//When
		Absence newAbsence = absenceMapper.toAbsence(absenceDto);

		//Then
		verifyAbsence(newAbsence);
	}

	private void verifyAbsence(Absence absence) {
		assertNotNull(absence);
		assertEquals(id, absence.getId());
		assertEquals(date, absence.getDate());
		assertEquals(reason, absence.getReason());
		assertEquals(childId, absence.getChildId());
	}

	private void verifyAbsenceDto(AbsenceDto absenceDto) {
		assertNotNull(absenceDto);
		assertEquals(id, absenceDto.getId());
		assertEquals(date, absenceDto.getDate());
		assertEquals(reason, absenceDto.getReason());
		assertEquals(childId, absenceDto.getChildId());
	}
}
