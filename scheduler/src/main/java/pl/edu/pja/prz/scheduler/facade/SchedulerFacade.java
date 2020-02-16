package pl.edu.pja.prz.scheduler.facade;

import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.SimpleScheduleDto;

import java.util.List;

public interface SchedulerFacade {
	List<JobInfoDto> findAvailableJobs();

	List<ScheduleJobInfoDto> findActiveJobs();

	List<ScheduleJobInfoDto> findActiveJobsByGroupName(String groupName);

	ScheduleJobInfoDto scheduleCronJob(CronScheduleDto scheduleDto);

	ScheduleJobInfoDto scheduleSimpleJob(SimpleScheduleDto scheduleDto);

	void unScheduleAllJobs();

	void unScheduleAllJobsByGroupName(String groupName);

	boolean startJob(String jobKey);

	boolean pauseJob(String jobKey);

	boolean resumeJob(String jobKey);

	boolean removeJob(String jobKey);
}
