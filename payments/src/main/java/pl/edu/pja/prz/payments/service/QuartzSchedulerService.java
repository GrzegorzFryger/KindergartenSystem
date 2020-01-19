package pl.edu.pja.prz.payments.service;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.payments.model.JobInfo;
import pl.edu.pja.prz.payments.model.ScheduleJobInfo;
import pl.edu.pja.prz.payments.utilites.QuartzFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuartzSchedulerService {
	private final SchedulerFactoryBean schedulerFactory;
	private final QuartzFactory quartzFactory;
	private final JobService jobService;

	public QuartzSchedulerService(SchedulerFactoryBean schedulerFactory, QuartzFactory quartzFactory,
	                              JobService jobService) {
		this.schedulerFactory = schedulerFactory;
		this.quartzFactory = quartzFactory;
		this.jobService = jobService;
	}

	public List<JobInfo> getAllDetailsAvailableJobs() {
		return jobService.getAllExistingJobs();
	}

	public List<ScheduleJobInfo> getAllActiveScheduleJobs() {
		return getScheduler().map(scheduler -> {
			try {
				return scheduler.getTriggerKeys(GroupMatcher.groupEquals(quartzFactory.getGroupName()))
						.stream()
						.map(triggerKey -> {

							try {
								return scheduler.getTrigger(triggerKey);
							} catch (SchedulerException e) {
								throw new IllegalArgumentException("Business exceptions, can not get scheduler");
							}

						})
						.map(trigger -> {
							JobDetail jobFromTrigger;

							try {
								jobFromTrigger = scheduler.getJobDetail(trigger.getJobKey());
							} catch (SchedulerException e) {
								throw new IllegalArgumentException("Business exceptions, can not get scheduler");
							}

							return new ScheduleJobInfo(
									jobFromTrigger.getKey().toString(),
									jobFromTrigger.getDescription(),
									String.valueOf(jobFromTrigger.isDurable()),
									trigger.getKey().toString(),
									trigger.getDescription(),
									trigger.getStartTime(),
									trigger.getEndTime()
							);
						})
						.collect(Collectors.toList());
			} catch (SchedulerException e) {
				throw new IllegalArgumentException("Business exceptions, can not get scheduler");
			}
		}).orElseThrow(() -> new IllegalArgumentException("Business exceptions, can not get scheduler"));

	}


	public ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression, boolean durability) {

		var jobInfo = jobService.getJobInfoByName(jobName)
				.orElseThrow(() -> new IllegalArgumentException("Not found job with name"));

		var trigger = quartzFactory.createCronTrigger(triggerDescription, cronExpression)
				.orElseThrow(() -> new IllegalArgumentException("Can not create trigger"));

		var jobDetails = quartzFactory.createJobDetail(jobInfo.getClassType(), jobInfo.getDescription(),
				durability, null)
				.orElseThrow(() -> new IllegalArgumentException("Can not create job"));

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.scheduleJob(jobDetails, trigger);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				},
				() -> {
					throw new IllegalArgumentException("Can not schedule cron Job");
				}
		);

		return new ScheduleJobInfo(
				jobDetails.getKey().toString(),
				jobDetails.getDescription(),
				String.valueOf(jobDetails.isDurable()),
				trigger.getKey().toString(),
				trigger.getDescription(),
				trigger.getStartTime(),
				trigger.getEndTime()
		);
	}

	public void unScheduleAllJobs() {
		getScheduler().ifPresentOrElse(scheduler -> {
					try {
						scheduler.unscheduleJobs(new ArrayList<>(
								scheduler.getTriggerKeys(GroupMatcher.groupEquals(quartzFactory.getGroupName()))
						));
					} catch (SchedulerException e) {
						throw new IllegalArgumentException("Business exceptions, can not get scheduler");
					}
				},
				() -> new IllegalArgumentException("Business exceptions, can not get scheduler")
		);
	}

	public void startJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.triggerJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}, IllegalArgumentException::new);
	}

	public void pauseJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.pauseJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}, IllegalArgumentException::new);
	}

	public void resumeJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.resumeJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}, IllegalArgumentException::new);
	}

	public void removeJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.deleteJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}, IllegalArgumentException::new);
	}


	private JobKey createJobKey(String key) {
		String[] arr = key.split("[.]");
		return new JobKey(arr[0], arr[1]);
	}

	private Optional<Scheduler> getScheduler() {
		return Optional.ofNullable(schedulerFactory.getObject());
	}
}
