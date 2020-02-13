package pl.edu.pja.prz.scheduler.service;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.util.List;
import java.util.Map;

public interface SchedulerService {
	List<JobInfo> getAllDetailsAvailableJobs();

	List<ScheduleJobInfo> getAllActiveScheduleJobs();

	List<ScheduleJobInfo> getAllActiveScheduleJobsByGroupName(String groupName);

	ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression,
	                                boolean durability, @Nullable String groupName, @Nullable Map<String, ?> dataToJob);

	void unScheduleAllJobs();

	void unScheduleAllJobsByGroup(String groupName);

	void startJob(String jobKey);

	void pauseJob(String jobKey);

	void resumeJob(String jobKey);

	void removeJob(String jobKey);
}
