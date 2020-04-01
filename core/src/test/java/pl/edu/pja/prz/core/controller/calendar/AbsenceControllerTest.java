package pl.edu.pja.prz.core.controller.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pja.prz.calendar.facade.AbsenceFacade;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;
import pl.edu.pja.prz.calendar.model.dto.AbsenceRangeDto;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_CALENDAR;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
public class AbsenceControllerTest {
	private Long id = 1L;
	private MockMvc mvc;
	@Mock
	private AbsenceFacade absenceFacade;
	@InjectMocks
	private AbsenceController absenceController;

	@BeforeEach
	public void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(absenceController).build();
	}

	@Test
	public void shouldDelegateApiCallTo_findAbsenceMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_CALENDAR + "absence/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).getAbsence(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_deleteAbsenceMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.delete(API_CALENDAR + "absence/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).deleteAbsence(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_createAbsenceMethod() throws Exception {
		//Given
		String json = convertToJson(new AbsenceDto());

		//When
		mvc.perform(MockMvcRequestBuilders.post(API_CALENDAR + "absence")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).createAbsence(any(AbsenceDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_updateAbsenceMethod() throws Exception {
		//Given
		String json = convertToJson(new AbsenceDto());

		//When
		mvc.perform(MockMvcRequestBuilders.put(API_CALENDAR + "absence")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).updateAbsence(any(AbsenceDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_getAllAbsencesByChildIdMethod() throws Exception {
		//Given
		String childId = UUID.randomUUID().toString();

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_CALENDAR + "absence/childById/" + childId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).getAllAbsencesByChildId(any(UUID.class));
	}

	@Test
	public void shouldDelegateApiCallTo_getAllAbsencesByDateMethod() throws Exception {
		//Given
		String date = LocalDate.now().toString();

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_CALENDAR + "absence/childByDate/" + date)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).getAllAbsencesByDate(any(LocalDate.class));
	}

	@Test
	public void shouldDelegateApiCallTo_getAllAbsencesForChildBetweenDatesMethod() throws Exception {
		//Given
		String childId = UUID.randomUUID().toString();
		String fromDate = LocalDate.now().toString();
		String toDate = LocalDate.now().toString();


		//When
		mvc.perform(MockMvcRequestBuilders.get(API_CALENDAR + "absence/child/" + childId + "/" + fromDate + "/" + toDate)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).getAllAbsencesForChildBetweenDates(
				any(UUID.class), any(LocalDate.class), any(LocalDate.class));
	}

	@Test
	public void shouldDelegateApiCallTo_getAllAbsencesBetweenDatesMethod() throws Exception {
		//Given
		String fromDate = LocalDate.now().toString();
		String toDate = LocalDate.now().toString();

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_CALENDAR + "absence/betweenDates/" + fromDate + "/" + toDate)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).getAllAbsencesBetweenDates(any(LocalDate.class), any(LocalDate.class));

	}

	@Test
	public void shouldDelegateApiCallTo_createAbsencesForChildBetweenDatesMethod() throws Exception {
		//Given
		UUID childId = UUID.randomUUID();
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now();
		String reason = "Reason";

		String json = convertToJson(new AbsenceRangeDto(childId, fromDate, toDate, reason));


		//When
		mvc.perform(MockMvcRequestBuilders.post(API_CALENDAR + "absences")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(absenceFacade, only()).createAbsencesForChildBetweenDates(any(AbsenceRangeDto.class));
	}
}
