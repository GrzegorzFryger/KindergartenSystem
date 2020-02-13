package pl.edu.pja.prz.scheduler.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import pl.edu.pja.prz.scheduler.service.JobService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzSchedulerConfiguration {

	@Bean("schedulerFactory")
	public SchedulerFactoryBean createSchedulerFactory(final ApplicationContext applicationContext,
	                                                   @Qualifier("schedulerProperties") Properties properties,
	                                                   @Qualifier("schedulerDataSource") DataSource dataSource) {
		CustomSpringBeanJobFactory jobFactory = new CustomSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		factory.setQuartzProperties(properties);
		factory.setJobFactory(jobFactory);
		return factory;
	}

	/**
	 * @param quartzProperties Allow inject configuration properties from properties file
	 */

	@Bean("schedulerProperties")
	public Properties createQuartzProperties(QuartzProperties quartzProperties) {
		var properties = new Properties();
		properties.putAll(quartzProperties.getProperties());
		return properties;
	}

	@Bean
	public JobService createJobService() {
		return new JobService("pl.edu.pja.prz");
	}

}
