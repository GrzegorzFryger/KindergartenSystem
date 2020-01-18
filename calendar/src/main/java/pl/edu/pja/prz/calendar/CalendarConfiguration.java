package pl.edu.pja.prz.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-calendar.properties")
public class CalendarConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(CalendarConfiguration.class);

	@PostConstruct
	public void postConstruct(){
		logger.info("LOAD CALENDAR MODULE");
	}
}
