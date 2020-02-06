package pl.edu.pja.prz.scheduler.service;

import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.util.List;

public interface SchedulerService {
	List<JobInfo> getAllDetailsAvailableJobs();

	List<ScheduleJobInfo> getAllActiveScheduleJobs();

	ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression, boolean durability);

	void unScheduleAllJobs();

	void startJob(String jobKey);

	void pauseJob(String jobKey);

	void resumeJob(String jobKey);

	void removeJob(String jobKey);
}
