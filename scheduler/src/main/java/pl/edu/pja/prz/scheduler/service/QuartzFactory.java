package pl.edu.pja.prz.scheduler.service;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Component
public class QuartzFactory {
	private static final Logger logger = LoggerFactory.getLogger(QuartzFactory.class);
	private String groupName = "DEFAULT";
	private final IdentifyGenerator identifyGenerator;
	private final ApplicationContext applicationContext;

	@Autowired
	public QuartzFactory(IdentifyGenerator identifyGenerator, ApplicationContext applicationContext) {
		this.identifyGenerator = identifyGenerator;
		this.applicationContext = applicationContext;
	}

	/**
	 * @param jobClassName
	 * @param description
	 * @param durability
	 * @param groupName
	 * @param dataToJob
	 * @return
	 */
	public Optional<JobDetail> createJobDetail(Class<? extends Job> jobClassName, String description,
	                                           boolean durability, @Nullable String groupName,
	                                           @Nullable Map<String, ?> dataToJob) {

		var jobFactory = new JobDetailFactoryBean();

		jobFactory.setJobClass(jobClassName);
		jobFactory.setName(identifyGenerator.generateId());

		if (groupName != null) {
			jobFactory.setGroup(groupName);
		} else {
			jobFactory.setGroup(this.groupName);
		}

		jobFactory.setDescription(description);
		jobFactory.setDurability(durability);

		if (dataToJob != null) {
			jobFactory.setJobDataAsMap(dataToJob);
		}

		jobFactory.setApplicationContext(applicationContext);
		jobFactory.afterPropertiesSet();

		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(jobFactory.getObject());
	}

	/**
	 * @param description
	 * @param cronExpression
	 * @param groupName
	 * @return
	 */
	public Optional<CronTrigger> createCronTrigger(String description, String cronExpression,
	                                               @Nullable String groupName) {
		var cronTriggerFactory = new CronTriggerFactoryBean();

		try {
			cronTriggerFactory.setBeanName(identifyGenerator.generateId());

			if (groupName != null) {
				cronTriggerFactory.setGroup(groupName);
			} else {
				cronTriggerFactory.setGroup(this.groupName);
			}

			cronTriggerFactory.setDescription(description);
			cronTriggerFactory.setCronExpression(cronExpression);
			cronTriggerFactory.afterPropertiesSet();
		} catch (ParseException pe) {
			//todo throw common exception
			logger.error("Failed to create cron trigger", pe);
		}
		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(cronTriggerFactory.getObject());
	}

	public Optional<SimpleTrigger> createSimpleTrigger(String description, LocalDateTime startDate,
	                                                   int repeatCount, @Nullable String groupName) {
		var simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();

		simpleTriggerFactoryBean.setBeanName(identifyGenerator.generateId());

		if (groupName != null) {
			simpleTriggerFactoryBean.setGroup(groupName);
		} else {
			simpleTriggerFactoryBean.setGroup(this.groupName);
		}
		simpleTriggerFactoryBean.setDescription(description);
		simpleTriggerFactoryBean.afterPropertiesSet();
		simpleTriggerFactoryBean.setStartTime(convertToDate(startDate));

		if (repeatCount > 0) {
			simpleTriggerFactoryBean.setRepeatCount(repeatCount);
		}

		return Optional.ofNullable(simpleTriggerFactoryBean.getObject());
	}

	private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return LocalDateTime.ofInstant(
				dateToConvert.toInstant(), ZoneId.systemDefault());
	}

	private Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date
				.from(dateToConvert.atZone(ZoneId.systemDefault())
						.toInstant());
	}

}
