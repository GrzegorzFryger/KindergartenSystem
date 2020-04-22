package pl.edu.pja.prz.scheduler.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class QuartzSchedulerService implements SchedulerService {
	private static final Logger logger = LoggerFactory.logger(QuartzSchedulerService.class);
	private static final String SCHEDULER_ERROR = "Can not get instance of QuartzScheduler" + " from application context in method: ";
	private static final String CAN_NOT_GET_TRIGGER = "Can not get Trigger";
	private final SchedulerFactoryBean schedulerFactory;
	private final QuartzFactory quartzFactory;
	private final JobService jobService;

	@Autowired
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
				return scheduler.getTriggerGroupNames()
						.stream()
						.map(this::getAllActiveScheduleJobsByGroupName)
						.flatMap(List::stream)
						.collect(Collectors.toList());
			} catch (SchedulerException e) {
				throw new IllegalArgumentException("Can not get TriggersKeys form scheduler");
			}
		}).orElseThrow(() -> new NullPointerException(SCHEDULER_ERROR + "ActiveScheduleJobs"));

	}

	@Override
	public List<ScheduleJobInfo> getAllActiveScheduleJobsByGroupName(String groupName) {
		return getScheduler().map(scheduler -> {
			try {
				return scheduler.getTriggerKeys(
						GroupMatcher.groupEquals(groupName)
				).stream().map(triggerKey -> {
					try {
						return scheduler.getTrigger(triggerKey);
					} catch (SchedulerException e) {
						logger.error("Failed get trigger with key - {}", triggerKey, e);
						throw new IllegalArgumentException(CAN_NOT_GET_TRIGGER);
					}
				}).map(trigger -> {
					JobDetail jobFromTrigger;
					try {
						jobFromTrigger = scheduler.getJobDetail(trigger.getJobKey());
					} catch (SchedulerException e) {
						logger.error("Failed get job from trigger with key - {}", trigger.getJobKey(), e);
						throw new IllegalArgumentException("Can not get JobDetail from scheduler");
					}
					return buildScheduleJobInfo(jobFromTrigger, trigger);
				}).collect(Collectors.toList());
			} catch (SchedulerException e) {
				throw new IllegalArgumentException("Can not get TriggersKeys form scheduler");
			}
		}).orElseThrow(() -> new NullPointerException(SCHEDULER_ERROR + "ActiveScheduleJobsByGroupName"));
	}

	/**
	 * @param jobName
	 * @param triggerDescription
	 * @param cronExpression
	 * @param durability
	 * @param groupName
	 * @param dataToJob
	 * @return
	 */

	@Override
	public ScheduleJobInfo scheduleCronJob(String jobName, String triggerDescription, String cronExpression,
	                                       boolean durability, @Nullable String groupName,
	                                       @Nullable Map<String, ?> dataToJob) {
		var jobInfo = jobService.getJobInfoByName(jobName)
				.orElseThrow(() -> new IllegalArgumentException("Can not get JobInfo"));

		var trigger = quartzFactory.createCronTrigger(triggerDescription, cronExpression, groupName)
				.orElseThrow(() -> new IllegalArgumentException(CAN_NOT_GET_TRIGGER));

		var jobDetails = quartzFactory.createJobDetail(jobInfo.getClassType(), jobInfo.getDescription(), durability,
				groupName, dataToJob).orElseThrow(() -> new IllegalArgumentException("Can not get JobDetails"));

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.scheduleJob(jobDetails, trigger);
					} catch (SchedulerException e) {
						logger.error("Failed to schedule cron a job with name - {}", jobName, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "scheduleCronJob");
				}
		);

		return buildScheduleJobInfo(jobDetails, trigger);
	}

	@Override
	public ScheduleJobInfo scheduleSimpleJob(String jobName, String triggerDescription, LocalDateTime startDate,
	                                         int repeatCount, @Nullable String groupName,
	                                         @Nullable Map<String, ?> dataToJob) {
		var jobInfo = jobService.getJobInfoByName(jobName)
				.orElseThrow(() -> new IllegalArgumentException("Can not get JobInfo"));

		var trigger = quartzFactory.createSimpleTrigger(triggerDescription, startDate, repeatCount, groupName)
				.orElseThrow(() -> new IllegalArgumentException(CAN_NOT_GET_TRIGGER));

		var jobDetails = quartzFactory.createJobDetail(jobInfo.getClassType(), jobInfo.getDescription(), false,
				groupName, dataToJob).orElseThrow(() -> new IllegalArgumentException("Can not get JobDetails"));

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.scheduleJob(jobDetails, trigger);
					} catch (SchedulerException e) {
						logger.error("Failed to schedule a job with name - {}", jobName, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "scheduleSimpleJob");
				}
		);

		return buildScheduleJobInfo(jobDetails, trigger);
	}

	@Override
	public void unScheduleAllJobs() {
		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.getTriggerGroupNames().forEach(this::unScheduleAllJobsByGroup);
					} catch (SchedulerException e) {
						logger.error("Failed unSchedule all Jobs  ", e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "unScheduleAllJobs");
				}
		);
	}

	@Override
	public void unScheduleAllJobsByGroup(String groupName) {
		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.unscheduleJobs(
								new ArrayList<>(scheduler.getTriggerKeys(
										GroupMatcher.groupEquals(groupName))
								)
						);
					} catch (SchedulerException e) {
						logger.error("Failed unSchedule all Jobs in group - {} ", groupName, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "unScheduleAllJobsByGroup");
				}
		);
	}

	@Override
	public boolean startJob(String jobKey) {
		AtomicBoolean success = new AtomicBoolean(false);
		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.triggerJob(createJobKey(jobKey));
					} catch (SchedulerException e) {
						logger.error("Failed start job - {}, ", jobKey, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "start job");
				}
		);

		return success.get();
	}

	@Override
	public boolean pauseJob(String jobKey) {
		AtomicBoolean success = new AtomicBoolean(false);
		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.pauseJob(createJobKey(jobKey));
					} catch (SchedulerException e) {
						logger.error("Failed pause job - {}, ", jobKey, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "pause job");
				}
		);

		return success.get();
	}

	@Override
	public boolean resumeJob(String jobKey) {
		AtomicBoolean success = new AtomicBoolean(false);
		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.resumeJob(createJobKey(jobKey));
					} catch (SchedulerException e) {
						logger.error("Failed resume job - {}, ", jobKey, e);
					}
				}, () -> {
					throw new NullPointerException(SCHEDULER_ERROR + "resume job");
				});

		return success.get();
	}

	@Override
	public boolean removeJob(String jobKey) {

		AtomicBoolean success = new AtomicBoolean(false);

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.deleteJob(createJobKey(jobKey));
						success.set(true);
					} catch (SchedulerException e) {
						logger.error("Failed remove job - {}, ", jobKey, e);
					}
				},
				() -> {
					throw new NullPointerException(SCHEDULER_ERROR + "remove job");
				}
		);
		return success.get();
	}

	private JobKey createJobKey(String key) {
		String[] arr = key.split("[.]");
		return new JobKey(arr[1], arr[0]);
	}

	private Optional<Scheduler> getScheduler() {
		return Optional.ofNullable(schedulerFactory.getObject());
	}

	private ScheduleJobInfo buildScheduleJobInfo(JobDetail jobDetails, Trigger trigger) {
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
}
