package pl.edu.pja.prz.scheduler.facade.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

@ExtendWith(MockitoExtension.class)
class SchedulerMapperTest {
  private ScheduleJobInfo scheduleJobInfo;
  private ScheduleJobInfoDto scheduleJobInfoDto;
  private JobInfo jobInfo;
  private JobInfoDto jobInfoDto;

  private SchedulerMapper schedulerMapper;

  @BeforeEach
  public void setUp() {
    schedulerMapper = new SchedulerMapperImpl();

    //First pair of model -> dto && dto -> model
    scheduleJobInfo = new ScheduleJobInfo();
    scheduleJobInfo.setJobKey("test job key");
    scheduleJobInfo.setJobDescription("test job description");
    scheduleJobInfo.setTriggerDescription("test trigger description");
    scheduleJobInfo.setIsDurable("durable");
    scheduleJobInfo.setTriggerKey("test trigger key");
    scheduleJobInfo.setTriggerStart(new Date(1584190894474L)); //Something around 14.03.2020
    scheduleJobInfo.setTriggerEnd(new Date(1584190952707L)); //Something around 14.03.2020

    scheduleJobInfoDto = new ScheduleJobInfoDto();
    scheduleJobInfoDto.setJobKey("test job key");
    scheduleJobInfoDto.setJobDescription("test job description");
    scheduleJobInfoDto.setTriggerDescription("test trigger description");
    scheduleJobInfoDto.setIsDurable("durable");
    scheduleJobInfoDto.setTriggerKey("test trigger key");
    scheduleJobInfoDto.setTriggerStart(new Date(1584190894474L)); //Something around 14.03.2020
    scheduleJobInfoDto.setTriggerEnd(new Date(1584190952707L)); //Something around 14.03.2020

    //Second pair of model -> dto && dto -> model
    jobInfo = new JobInfo();
    jobInfo.setDescription("test description");
    jobInfo.setName("test name");

    jobInfoDto = new JobInfoDto();
    jobInfoDto.setDescription("test description");
    jobInfoDto.setName("test name");
  }

  @Test
  public void Should_Map_ScheduleJobInfoDto_To_ScheduleJobInfo() {
    //Given

    //When
    ScheduleJobInfo result = schedulerMapper.toScheduleJobInfo(scheduleJobInfoDto);

    //Then
    assertNotNull(result);
    assertEquals("test job key", result.getJobKey());
    assertEquals("test job description", result.getJobDescription());
    assertEquals("test trigger description", result.getTriggerDescription());
    assertEquals("durable", result.getIsDurable());
    assertEquals("test trigger key", result.getTriggerKey());
    assertEquals(new Date(1584190894474L), result.getTriggerStart());
    assertEquals(new Date(1584190952707L), result.getTriggerEnd());
  }

  @Test
  public void Should_Map_ScheduleJobInfo_To_ScheduleJobInfoDto() {
    //Given

    //When
    ScheduleJobInfoDto result = schedulerMapper.fromScheduleJobInfo(scheduleJobInfo);

    //Then
    assertNotNull(result);
    assertEquals("test job key", result.getJobKey());
    assertEquals("test job description", result.getJobDescription());
    assertEquals("test trigger description", result.getTriggerDescription());
    assertEquals("durable", result.getIsDurable());
    assertEquals("test trigger key", result.getTriggerKey());
    assertEquals(new Date(1584190894474L), result.getTriggerStart());
    assertEquals(new Date(1584190952707L), result.getTriggerEnd());
  }

  @Test
  public void Should_Map_JobInfoDto_To_JobInfo() {
    //Given

    //When
    JobInfo result = schedulerMapper.toJobInfo(jobInfoDto);

    //Then
    assertNotNull(result);
    assertEquals("test description", result.getDescription());
    assertEquals("test name", result.getName());
  }

  @Test
  public void Should_Map_JobInfo_To_JobInfoDto() {
    //Given

    //When
    JobInfoDto result = schedulerMapper.fromJobInfo(jobInfo);

    //Then
    assertNotNull(result);
    assertEquals("test description", result.getDescription());
    assertEquals("test name", result.getName());
  }

}
