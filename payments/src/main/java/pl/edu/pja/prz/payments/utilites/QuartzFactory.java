package pl.edu.pja.prz.payments.utilites;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

@Component
public class QuartzFactory {
	private String groupName = "DEFAULT";
	private final IdentifyGenerator identifyGenerator;
	private transient ApplicationContext applicationContext;

	@Autowired
	public QuartzFactory(IdentifyGenerator identifyGenerator, ApplicationContext applicationContext) {
		this.identifyGenerator = identifyGenerator;
		this.applicationContext = applicationContext;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Optional<JobDetail> createJobDetail(Class<? extends Job> jobClassName, String description, boolean durability,
	                                           @Nullable Map<String, ?> dataToJob) {

		var jobFactory = new JobDetailFactoryBean();

		jobFactory.setJobClass(jobClassName);
		jobFactory.setName(identifyGenerator.generateId());
		jobFactory.setGroup(groupName);
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

	public Optional<CronTrigger> createCronTrigger(String description, String cronExpression) {
		var cronTriggerFactory = new CronTriggerFactoryBean();

		try {
			cronTriggerFactory.setBeanName(identifyGenerator.generateId());
			cronTriggerFactory.setGroup(groupName);
			cronTriggerFactory.setDescription(description);
			cronTriggerFactory.setCronExpression(cronExpression);
			cronTriggerFactory.afterPropertiesSet();
		} catch (ParseException e) {
			//todo throw common exception
			e.printStackTrace();
		}
		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(cronTriggerFactory.getObject());
	}
}
