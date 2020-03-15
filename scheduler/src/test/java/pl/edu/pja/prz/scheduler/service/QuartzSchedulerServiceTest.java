package pl.edu.pja.prz.scheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.CronTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import pl.edu.pja.prz.scheduler.model.JobInfo;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuartzSchedulerServiceTest {
    @Mock
    private SchedulerFactoryBean schedulerFactory;
    @Mock
    private QuartzFactory quartzFactory;
    @Mock
    private JobService jobService;

    private QuartzSchedulerService quartzSchedulerService;

    @BeforeEach
    public void setUp() {
        quartzSchedulerService = new QuartzSchedulerService(schedulerFactory, quartzFactory, jobService);
    }

    @Test
    public void Should_GetAllDetailsAvailableJobs() {
        //Given

        //When
        quartzSchedulerService.getAllDetailsAvailableJobs();

        //Then
        verify(jobService, only()).getAllExistingJobs();
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_GetAllActiveScheduleJobsMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.getAllActiveScheduleJobs();
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_GetAllActiveScheduleJobsByGroupNameMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.getAllActiveScheduleJobsByGroupName("Some group name");
        });
    }

    @Test
    public void Should_ThrowException_When_JobInfo_For_ScheduleCronJob_DoesNotExist() {
        //Given

        //When
        when(jobService.getJobInfoByName(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            quartzSchedulerService.scheduleCronJob(
                    "Some job name",
                    "Some trigger description",
                    "Some cron expression",
                    false,
                    "Some group name",
                    new HashMap<>()
            );
        });
    }

    @Test
    public void Should_ThrowException_When_CronTrigger_For_ScheduleCronJob_DoesNotExist() {
        //Given
        JobInfo jobInfo = new JobInfo();

        //When
        when(jobService.getJobInfoByName(anyString()))
                .thenReturn(Optional.of(jobInfo));
        when(quartzFactory.createCronTrigger(anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            quartzSchedulerService.scheduleCronJob(
                    "Some job name",
                    "Some trigger description",
                    "Some cron expression",
                    false,
                    "Some group name",
                    new HashMap<>()
            );
        });
    }

    @Test
    public void Should_ThrowException_When_JobDetail_For_ScheduleCronJob_DoesNotExist() {
        //Given
        JobInfo jobInfo = new JobInfo();
        jobInfo.setClassType(Object.class);
        jobInfo.setDescription("Some description");
        CronTrigger cronTrigger = new CronTriggerImpl();

        //When
        when(jobService.getJobInfoByName(anyString()))
                .thenReturn(Optional.of(jobInfo));
        when(quartzFactory.createCronTrigger(anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(cronTrigger));
        when(quartzFactory.createJobDetail(any(), anyString(), anyBoolean(), anyString(), anyMap()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            quartzSchedulerService.scheduleCronJob(
                    "Some job name",
                    "Some trigger description",
                    "Some cron expression",
                    false,
                    "Some group name",
                    new HashMap<>()
            );
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_UnScheduleAllJobsMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.unScheduleAllJobs();
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_UnScheduleAllJobsByGroupMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.unScheduleAllJobsByGroup("Some group name");
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_StartJobMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.startJob("Some job key");
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_PauseJobMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.pauseJob("Some job key");
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_ResumeJobMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.resumeJob("Some job key");
        });
    }

    @Test
    public void Should_ThrowException_When_SchedulerIsNotAvailable_For_RemoveJobMethod() {
        //Given

        //When
        when(schedulerFactory.getObject()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            quartzSchedulerService.removeJob("Some job key");
        });
    }
}
