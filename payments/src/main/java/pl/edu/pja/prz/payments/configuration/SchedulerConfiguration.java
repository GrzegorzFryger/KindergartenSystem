package pl.edu.pja.prz.payments.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SchedulerConfiguration {

	/**
	 * Spring offers a SchedulerFactoryBean that exposes triggers to be set as properties.
	 * SchedulerFactoryBean schedules the actual jobs with those triggers.
	 * <p>
	 * FactoryBean that creates and configures a Quartz Scheduler, manages its lifecycle as part of the Spring application context, and exposes the Scheduler as bean reference for dependency injection.
	 * Allows registration of JobDetails, Calendars and Triggers, automatically starting the scheduler on initialization and shutting it down on destruction.
	 * In scenarios that just require static registration of jobs at startup, there is no need to access the Scheduler instance itself in application code.
	 * <p>
	 * For dynamic registration of jobs at runtime, use a bean reference to this SchedulerFactoryBean to get direct access to the Quartz Scheduler (org.quartz.Scheduler).
	 * This allows you to create new jobs and triggers, and also to control and monitor the entire Scheduler.
	 * <p>
	 * Note that Quartz instantiates a new Job for each execution, in contrast to Timer which uses a TimerTask instance that is shared between repeated executions.
	 * Just JobDetail descriptors are shared.
	 * <p>
	 * When using persistent jobs, it is strongly recommended to perform all operations on the Scheduler within Spring-managed (or plain JTA) transactions.
	 * Else, database locking will not properly work and might even break. (See setDataSource javadoc for details.)
	 * <p>
	 * The preferred way to achieve transactional execution is to demarcate declarative transactions at the business facade level,
	 * which will automatically apply to Scheduler operations performed within those scopes. Alternatively, you may add transactional advice for the Scheduler itself.
	 *
	 * @param dataSource payments data source
	 */

	@Bean(name = "scheduleFactory")
	public SchedulerFactoryBean createSchedulerFactory(final ApplicationContext applicationContext, Properties properties,
	                                                   @Qualifier("paymentDataSource") DataSource dataSource) {

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
	@Bean
	public Properties createQuartzProperties(QuartzProperties quartzProperties) {
		var properties = new Properties();
		properties.putAll(quartzProperties.getProperties());
		return properties;
	}

	@Bean
	public JobDetailFactoryBean createJobFactory() {
		var jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setGroup("PAYMENTS");
		return  jobDetailFactory;
	}
}
