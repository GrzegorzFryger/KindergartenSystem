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
import pl.edu.pja.prz.calendar.facade.DayOffWorkFacade;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
public class DayOffWorkControllerTest {
	private static final String BASE = "/api/calendar/";
	private Long id = 1L;
	private MockMvc mvc;
	@Mock
	private DayOffWorkFacade dayOffWorkFacade;
	@InjectMocks
	private DayOffWorkController dayOffWorkController;

	@BeforeEach
	public void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(dayOffWorkController).build();
	}

	@Test
	public void shouldDelegateApiCallTo_findDayOffWorkMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(BASE + "dayoff/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(dayOffWorkFacade, only()).getDayOffWork(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_deleteDayOffWorkMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.delete(BASE + "dayoff/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(dayOffWorkFacade, only()).deleteDayOffWork(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_createDayOffWorkMethod() throws Exception {
		//Given
		String json = convertToJson(new DayOffWorkDto());

		//When
		mvc.perform(MockMvcRequestBuilders.post(BASE + "dayoff")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(dayOffWorkFacade, only()).createDayOffWork(any(DayOffWorkDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_updateDayOffWorkMethod() throws Exception {
		//Given
		String json = convertToJson(new DayOffWorkDto());

		//When
		mvc.perform(MockMvcRequestBuilders.put(BASE + "dayoff")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(dayOffWorkFacade, only()).updateDayOffWork(any(DayOffWorkDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_findAllDaysOffWorkMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(BASE + "daysoff")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(dayOffWorkFacade, only()).getAllDaysOff();
	}
}
