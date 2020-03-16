package pl.edu.pja.prz.scheduler.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;
import pl.edu.pja.prz.scheduler.facade.dto.SimpleScheduleDto;
import pl.edu.pja.prz.scheduler.facade.mapper.SchedulerMapper;
import pl.edu.pja.prz.scheduler.facade.mapper.SchedulerMapperImpl;
import pl.edu.pja.prz.scheduler.service.SchedulerService;

@ExtendWith(MockitoExtension.class)
class SchedulerFacadeImplTest {
  @Mock
  private SchedulerService schedulerService;

  private SchedulerMapper schedulerMapper = new SchedulerMapperImpl();

  private SchedulerFacade schedulerFacade;

  @BeforeEach
  public void setUp() {
    schedulerFacade = new SchedulerFacadeImpl(schedulerService, schedulerMapper);
  }

  @Test
  public void Should_FindAllAvailableJobs() {
    //Given

    //When
    schedulerFacade.findAvailableJobs();

    //Then
    verify(schedulerService, only()).getAllDetailsAvailableJobs();
  }

  @Test
  public void Should_FindAllActiveJobs() {
    //Given

    //When
    schedulerFacade.findActiveJobs();

    //Then
    verify(schedulerService, only()).getAllActiveScheduleJobs();
  }

  @Test
  public void Should_FindActiveJobsByGroupName() {
    //Given

    //When
    schedulerFacade.findActiveJobsByGroupName("Some group name");

    //Then
    verify(schedulerService, only()).getAllActiveScheduleJobsByGroupName(anyString());
  }

  @Test
  public void Should_ScheduleCronJob() {
    //Given
    CronScheduleDto cronScheduleDto = new CronScheduleDto();
    cronScheduleDto.setJobName("Some Job");
    cronScheduleDto.setTriggerDescription("Trigger Description");
    cronScheduleDto.setCronExpression("Somoe cron expression");
    cronScheduleDto.setGroupName("Some group name");
    cronScheduleDto.setDurability(true);
    cronScheduleDto.setDataForJob(new HashMap<>());


    //When
    schedulerFacade.scheduleCronJob(cronScheduleDto);

    //Then
    verify(schedulerService, only()).scheduleCronJob(
        anyString(), anyString(), anyString(), anyBoolean(), anyString(), anyMap());
  }

  @Test
  public void Should_ScheduleSimpleJob() {
    //Given
    SimpleScheduleDto simpleScheduleDto = new SimpleScheduleDto();
    simpleScheduleDto.setJobName("Some Job");
    simpleScheduleDto.setTriggerDescription("Trigger Description");
    simpleScheduleDto.setStartDate(LocalDateTime.now());
    simpleScheduleDto.setGroupName("Some group name");
    simpleScheduleDto.setRepeatCount(100);
    simpleScheduleDto.setDataForJob(new HashMap<>());


    //When
    schedulerFacade.scheduleSimpleJob(simpleScheduleDto);

    //Then
    verify(schedulerService, only()).scheduleSimpleJob(
        anyString(), anyString(), any(LocalDateTime.class), anyInt(), anyString(), anyMap());
  }

  @Test
  public void Should_UnScheduleAllJobs() {
    //Given

    //When
    schedulerFacade.unScheduleAllJobs();

    //Then
    verify(schedulerService, only()).unScheduleAllJobs();
  }

  @Test
  public void Should_UnScheduleAllJobsByGroupName() {
    //Given

    //When
    schedulerFacade.unScheduleAllJobsByGroupName("SOME GROUP NAME");

    //Then
    verify(schedulerService, only()).unScheduleAllJobsByGroup(anyString());
  }

  @Test
  public void Should_StartJob() {
    //Given

    //When
    schedulerFacade.startJob("SOME JOB KEY");

    //Then
    verify(schedulerService, only()).startJob(anyString());
  }

  @Test
  public void Should_PauseJob() {
    //Given

    //When
    schedulerFacade.pauseJob("SOME JOB KEY");

    //Then
    verify(schedulerService, only()).pauseJob(anyString());
  }

  @Test
  public void Should_ResumeJob() {
    //Given

    //When
    schedulerFacade.resumeJob("SOME JOB KEY");

    //Then
    verify(schedulerService, only()).resumeJob(anyString());
  }

  @Test
  public void Should_RemoveJob() {
    //Given

    //When
    schedulerFacade.removeJob("SOME JOB KEY");

    //Then
    verify(schedulerService, only()).removeJob(anyString());
  }
}
