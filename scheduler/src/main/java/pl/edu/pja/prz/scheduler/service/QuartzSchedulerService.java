package pl.edu.pja.prz.scheduler.service;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuartzSchedulerService implements SchedulerService {
	private final SchedulerFactoryBean schedulerFactory;
	private final QuartzFactory quartzFactory;
	private final JobService jobService;

	public QuartzSchedulerService(SchedulerFactoryBean schedulerFactory, QuartzFactory quartzFactory,
	                              JobService jobService) {
		this.schedulerFactory = schedulerFactory;
		this.quartzFactory = quartzFactory;
		this.jobService = jobService;
	}

	@Override
	public List<JobInfo> getAllDetailsAvailableJobs() {
		return jobService.getAllExistingJobs();
	}

	@Override
	public List<ScheduleJobInfo> getAllActiveScheduleJobs() {
		return getScheduler().map(scheduler -> {
			try {
				return scheduler.getTriggerKeys(GroupMatcher.groupEquals(quartzFactory.getGroupName()))
						.stream()
						.map(triggerKey -> {

							try {
								return scheduler.getTrigger(triggerKey);
							} catch (SchedulerException e) {
								throw new BusinessException("Can not get Trigger");
							}

						})
						.map(trigger -> {
							JobDetail jobFromTrigger;

							try {
								jobFromTrigger = scheduler.getJobDetail(trigger.getJobKey());
							} catch (SchedulerException e) {
								throw new BusinessException("Can not get JobDetail from scheduler");
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
				throw new BusinessException("Can not get TriggersKeys form scheduler");
			}
		}).orElseThrow(() -> new BusinessException("Can not get Scheduler"));

	}


	@Override
	public ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression,
	                                       boolean durability) {

		var jobInfo = jobService.getJobInfoByName(jobName)
				.orElseThrow(() -> new BusinessException("Can not get JobInfo"));

		var trigger = quartzFactory.createCronTrigger(triggerDescription, cronExpression)
				.orElseThrow(() -> new BusinessException("Can not get Trigger"));

		var jobDetails = quartzFactory.createJobDetail(jobInfo.getClassType(), jobInfo.getDescription(),
				durability, null)
				.orElseThrow(() -> new BusinessException("Can not get JobDetails"));

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.scheduleJob(jobDetails, trigger);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				},
				() -> {
					throw new BusinessException("Can not get JobDetails");
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

	@Override
	public void unScheduleAllJobs() {
		getScheduler().ifPresentOrElse(scheduler -> {
					try {
						scheduler.unscheduleJobs(new ArrayList<>(
								scheduler.getTriggerKeys(GroupMatcher.groupEquals(quartzFactory.getGroupName()))
						));
					} catch (SchedulerException e) {
						throw new BusinessException("Can not get Trigger form scheduler");
					}
				},
				() -> new BusinessException("Can not get Trigger Scheduler")
		);
	}

	@Override
	public void startJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.triggerJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}, () -> new BusinessException("Can not get Scheduler"));
	}

	@Override
	public void pauseJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.pauseJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				throw new BusinessException("Can not pauseJob");
			}
		}, () -> new BusinessException("Can not get Scheduler"));
	}

	@Override
	public void resumeJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.resumeJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				throw new BusinessException("Can not resumeJob");
			}
		}, () -> new BusinessException("Can not get Scheduler"));
	}

	@Override
	public void removeJob(String jobKey) {
		getScheduler().ifPresentOrElse(scheduler -> {
			try {
				scheduler.deleteJob(createJobKey(jobKey));
			} catch (SchedulerException e) {
				throw new BusinessException("Can not deleteJob");
			}
		}, () -> new BusinessException("Can not get Scheduler"));
	}


	private JobKey createJobKey(String key) {
		String[] arr = key.split("[.]");
		return new JobKey(arr[1], arr[0]);
	}

	private Optional<Scheduler> getScheduler() {
		return Optional.ofNullable(schedulerFactory.getObject());
	}
}
