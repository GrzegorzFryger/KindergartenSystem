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
import java.util.ArrayList;
import java.util.List;
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

	@Test
	public void ShouldMapFromList() {
		//Given
		List<Absence> absenceList = new ArrayList<>();
		absenceList.add(absence);

		//When
		List<AbsenceDto> result = absenceMapper.absenceListToDtoList(absenceList);

		//Then
		verifyAbsenceDtoList(result);
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

	private void verifyAbsenceDtoList(List<AbsenceDto> absenceDtoList) {
		assertNotNull(absenceDtoList);
		List<AbsenceDto> listForComparison = new ArrayList<>();
		listForComparison.add(absenceDto);
		assertEquals(listForComparison.get(0).getReason(), absenceDtoList.get(0).getReason());
		assertEquals(listForComparison.get(0).getDate(), absenceDtoList.get(0).getDate());
		assertEquals(listForComparison.get(0).getChildId(), absenceDtoList.get(0).getChildId());
		assertEquals(listForComparison.size(), absenceDtoList.size());
	}
}
