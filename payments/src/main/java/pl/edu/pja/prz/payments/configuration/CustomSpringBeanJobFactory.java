package pl.edu.pja.prz.payments.configuration;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Spring wrapper for Quartz
 * Supports Spring-style dependency injection on bean properties.
 * This is essentially the direct equivalent of Spring's QuartzJobBean in the shape of a Quartz JobFactory.
* */

public final class CustomSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private transient AutowireCapableBeanFactory beanFactory;

	@Override
	public void setApplicationContext(final ApplicationContext context) {
		beanFactory = context.getAutowireCapableBeanFactory();
	}

	/**
	 *
	 * protected Object createJobInstance(TriggerFiredBundle bundle)
	 * throws Exception
	 * Create the job instance, populating it with property values taken from the scheduler context, job data map and trigger data map.
	 * Overrides:
	 * createJobInstance in class AdaptableJobFactory
	 * Parameters:
	 * bundle - the TriggerFiredBundle from which the JobDetail and other info relating to the trigger firing can be obtained
	 * Returns:
	 * the job instance
	 * Throws:
	 * Exception - if job instantiation failed
	 */

	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job);
		return job;
	}


}