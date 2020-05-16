package pl.edu.pja.prz.core.controller.technical;

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
import pl.edu.pja.prz.scheduler.facade.SchedulerFacade;
import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_SCHEDULER;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class SchedulerControllerTest {

    private MockMvc mvc;

    @Mock
    private SchedulerFacade schedulerFacade;

    @InjectMocks
    private SchedulerController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_findAvailableJobsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_SCHEDULER + "/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).findAvailableJobs();
    }

    @Test
    public void Should_DelegateApiCallTo_findActiveJobsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_SCHEDULER + "/active")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).findActiveJobs();
    }

    @Test
    public void Should_DelegateApiCallTo_unScheduleAllJobsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_SCHEDULER + "/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).unScheduleAllJobs();
    }

    @Test
    public void Should_DelegateApiCallTo_scheduleCronJobMethod() throws Exception {
        //Given
        String json = convertToJson(new CronScheduleDto());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_SCHEDULER + "/cron")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).scheduleCronJob(any(CronScheduleDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_startJobMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_SCHEDULER + "/start/someJobKey")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).startJob(anyString());
    }

    @Test
    public void Should_DelegateApiCallTo_pauseJobMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_SCHEDULER + "/pause/someJobKey")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).pauseJob(anyString());
    }

    @Test
    public void Should_DelegateApiCallTo_resumeJobMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_SCHEDULER + "/resume/someJobKey")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).resumeJob(anyString());
    }

    @Test
    public void Should_DelegateApiCallTo_removeJobMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_SCHEDULER + "/remove/someJobKey")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(schedulerFacade, only()).removeJob(anyString());
    }
}