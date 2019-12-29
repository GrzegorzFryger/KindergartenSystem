package pl.edu.pja.prz.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-meal.properties")
public class MealConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MealConfiguration.class);

	@PostConstruct
	public void postConstruct() {
		logger.info("LOAD MEAL MODULE");
	}
}
