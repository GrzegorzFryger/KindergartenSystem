package pl.edu.pja.prz.scheduler.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuartzSchedulerService implements SchedulerService {
	private static final Logger logger = LoggerFactory.logger(QuartzSchedulerService.class);

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
				throw new BusinessException("Can not get TriggersKeys form scheduler");
			}
		}).orElseThrow(() -> new BusinessException("Can not get Scheduler"));

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
						throw new BusinessException("Can not get Trigger");
					}
				}).map(trigger -> {
					JobDetail jobFromTrigger;
					try {
						jobFromTrigger = scheduler.getJobDetail(trigger.getJobKey());
					} catch (SchedulerException e) {
						throw new BusinessException("Can not get JobDetail from scheduler");
					}
					return buildScheduleJobInfo(jobFromTrigger, trigger);
				}).collect(Collectors.toList());
			} catch (SchedulerException e) {
				throw new BusinessException("Can not get TriggersKeys form scheduler");
			}
		}).orElseThrow(() -> new BusinessException("Can not get Scheduler"));
	}

	/**
	 *
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
				.orElseThrow(() -> new BusinessException("Can not get JobInfo"));

		var trigger = quartzFactory.createCronTrigger(triggerDescription, cronExpression, groupName)
				.orElseThrow(() -> new BusinessException("Can not get Trigger"));

		var jobDetails = quartzFactory.createJobDetail(jobInfo.getClassType(), jobInfo.getDescription(), durability,
				groupName, dataToJob).orElseThrow(() -> new BusinessException("Can not get JobDetails"));

		getScheduler().ifPresentOrElse(
				scheduler -> {
					try {
						scheduler.scheduleJob(jobDetails, trigger);
					} catch (SchedulerException e) {
						logger.error("Failed to schedule a job", e);
					}
				},
				() -> {
					throw new BusinessException("Can not get JobDetails");
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
                        throw new BusinessException("Failed to unschedule jobs for all groups");
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Trigger Scheduler");
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
                        throw new BusinessException("Failed to unschedule jobs for group: " + groupName);
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Trigger Scheduler");
                }
        );
    }

    @Override
    public void startJob(String jobKey) {
        getScheduler().ifPresentOrElse(
                scheduler -> {
                    try {
                        scheduler.triggerJob(createJobKey(jobKey));
                    } catch (SchedulerException e) {
                        throw new BusinessException("Failed to start job: " + jobKey);
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Scheduler");
                }
        );
    }

    @Override
    public void pauseJob(String jobKey) {
        getScheduler().ifPresentOrElse(
                scheduler -> {
                    try {
                        scheduler.pauseJob(createJobKey(jobKey));
                    } catch (SchedulerException e) {
                        throw new BusinessException("Failed to pause job: " + jobKey);
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Scheduler");
                }
        );
    }

    @Override
    public void resumeJob(String jobKey) {
        getScheduler().ifPresentOrElse(
                scheduler -> {
                    try {
                        scheduler.resumeJob(createJobKey(jobKey));
                    } catch (SchedulerException e) {
                        throw new BusinessException("Failed to resume job: " + jobKey);
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Scheduler");
                }
        );
    }

    @Override
    public void removeJob(String jobKey) {
        getScheduler().ifPresentOrElse(
                scheduler -> {
                    try {
                        scheduler.deleteJob(createJobKey(jobKey));
                    } catch (SchedulerException e) {
                        throw new BusinessException("Failed to delete job: " + jobKey);
                    }
                },
                () -> {
                    throw new BusinessException("Can not get Scheduler");
                }
        );
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
