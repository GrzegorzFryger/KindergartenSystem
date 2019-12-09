package pl.edu.pja.tuition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;


@Configuration
@ComponentScan
@PropertySource("classpath:application-tuition.properties")
public class TuitionConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(TuitionConfiguration.class);

	@PostConstruct
	public void postConstruct(){
		logger.info("LOAD TUITION MODULE");
	}
}
