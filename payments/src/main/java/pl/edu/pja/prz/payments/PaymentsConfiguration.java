package pl.edu.pja.prz.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-payments.properties")
public class PaymentsConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(PaymentsConfiguration.class);

	@PostConstruct
	public void postConstruct(){
		logger.info("LOAD PAYMENTS MODULE");
	}
}
