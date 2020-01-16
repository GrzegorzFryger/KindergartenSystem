package pl.edu.pja.prz.payments.utilites;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobDetailsFactory {
	private final JobDetailFactoryBean jobFactory;
	private transient ApplicationContext applicationContext;
	public final static String GROUP_NAME = "payments" ;

	@Autowired
	public JobDetailsFactory(JobDetailFactoryBean jobFactory, ApplicationContext applicationContext) {
		this.jobFactory = jobFactory;
		this.applicationContext = applicationContext;
	}

	public Optional<JobDetail> ceateJobDetail(String className, String name,  String description, boolean durability) {
		try {
			jobFactory.setJobClass((Class<? extends Job>) Class.forName(className));
			jobFactory.setName(name);
			jobFactory.setGroup(GROUP_NAME);
			jobFactory.setDescription(description);
			jobFactory.setDurability(durability);
			jobFactory.setApplicationContext(applicationContext);
			jobFactory.afterPropertiesSet();
		} catch (ClassNotFoundException e) {
			//todo throw common exception
			e.printStackTrace();
		}
		//todo handle with exceptions, and check isSingleton
		return Optional.ofNullable(jobFactory.getObject());
	}

}
