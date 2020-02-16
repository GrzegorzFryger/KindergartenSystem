package pl.edu.pja.prz.scheduler.service;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SchedulerService {
	List<JobInfo> getAllDetailsAvailableJobs();

	List<ScheduleJobInfo> getAllActiveScheduleJobs();

	List<ScheduleJobInfo> getAllActiveScheduleJobsByGroupName(String groupName);

	ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression,
	                                boolean durability, @Nullable String groupName,
	                                @Nullable Map<String, ?> dataToJob);

	ScheduleJobInfo scheduleSimpleJob(String jobName, String triggerDescription, LocalDateTime startDate,
	                                  int repeatCount, @Nullable String groupName,
	                                  @Nullable Map<String, ?> dataToJob);

	void unScheduleAllJobs();

	void unScheduleAllJobsByGroup(String groupName);

	boolean startJob(String jobKey);

	boolean pauseJob(String jobKey);

	boolean resumeJob(String jobKey);

	boolean removeJob(String jobKey);
}
