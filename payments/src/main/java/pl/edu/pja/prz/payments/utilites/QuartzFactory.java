package pl.edu.pja.prz.payments.utilites;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Optional;

@Component
public class QuartzFactory {
	private final JobDetailFactoryBean jobFactory;
	private final CronTriggerFactoryBean cronTriggerFactory;
	private transient ApplicationContext applicationContext;

	public final static String GROUP_NAME = "payments" ;

	@Autowired
	public QuartzFactory(JobDetailFactoryBean jobFactory, CronTriggerFactoryBean cronTriggerFactory,
	                     ApplicationContext applicationContext) {
		this.jobFactory = jobFactory;
		this.cronTriggerFactory = cronTriggerFactory;
		this.applicationContext = applicationContext;
	}

	public Optional<JobDetail> ceateJobDetail(String jobClassName, String name,  String description,
	                                          boolean durability) {
		try {
			jobFactory.setJobClass((Class<? extends Job>) Class.forName(jobClassName));
			jobFactory.setName(name);
			jobFactory.setGroup(GROUP_NAME);
			jobFactory.setDescription(description);
			jobFactory.setDurability(durability);
			jobFactory.setApplicationContext(applicationContext);
			jobFactory.afterPropertiesSet();
		} catch (ClassNotFoundException  e) {
			//todo throw common exception
			e.printStackTrace();
		}
		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(jobFactory.getObject());
	}

	public Optional<CronTrigger> createCronTriger(String name,  String description, String cronExpression) {
		try {
			cronTriggerFactory.setBeanName(name);
			cronTriggerFactory.setGroup(GROUP_NAME);
			cronTriggerFactory.setDescription(description);
			cronTriggerFactory.setCronExpression(cronExpression);
			cronTriggerFactory.afterPropertiesSet();
		} catch (ParseException e) {
			//todo throw common exception
			e.printStackTrace();
		}

		return Optional.ofNullable(cronTriggerFactory.getObject());
	}

}
