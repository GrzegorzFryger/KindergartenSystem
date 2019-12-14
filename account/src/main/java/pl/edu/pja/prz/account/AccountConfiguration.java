package pl.edu.pja.prz.account;

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
@PropertySource("classpath:application-account.properties")
public class AccountConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AccountConfiguration.class);

	@PostConstruct
	public void postConstruct() {
		logger.info("Load ACCOUNT MODULE");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
