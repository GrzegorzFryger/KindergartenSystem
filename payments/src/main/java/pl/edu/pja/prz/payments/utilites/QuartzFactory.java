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
	public final static String GROUP_NAME = "PAYMENT";
	private final IdentifyGenerator identifyGenerator;
	private transient ApplicationContext applicationContext;

	@Autowired
	public QuartzFactory(IdentifyGenerator identifyGenerator, ApplicationContext applicationContext) {
		this.identifyGenerator = identifyGenerator;
		this.applicationContext = applicationContext;
	}

	public Optional<JobDetail> createJobDetail(String jobClassName, String description, boolean durability) {

		var jobFactory = new JobDetailFactoryBean();

		jobFactory.setJobClass(getClass(jobClassName));
		jobFactory.setName(identifyGenerator.generateId());
		jobFactory.setGroup(GROUP_NAME);
		jobFactory.setDescription(description);
		jobFactory.setDurability(durability);
		jobFactory.setApplicationContext(applicationContext);
		jobFactory.afterPropertiesSet();

		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(jobFactory.getObject());
	}

	public Optional<CronTrigger> createCronTigger(String description, String cronExpression) {
		var cronTriggerFactory = new CronTriggerFactoryBean();

		try {
			cronTriggerFactory.setBeanName(identifyGenerator.generateId());
			cronTriggerFactory.setGroup(GROUP_NAME);
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

	@SuppressWarnings("unchecked")
	private Class<? extends Job> getClass(String nameClass) {
		try {
			return (Class<? extends Job>) Class.forName(nameClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}

}
