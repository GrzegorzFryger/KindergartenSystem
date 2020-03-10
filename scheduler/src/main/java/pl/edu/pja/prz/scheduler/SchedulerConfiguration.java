package pl.edu.pja.prz.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-scheduler.properties")
public class SchedulerConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

	@PostConstruct
	public void postConstruct() {
		logger.info("LOAD SCHEDULER MODULE");
	}
}


