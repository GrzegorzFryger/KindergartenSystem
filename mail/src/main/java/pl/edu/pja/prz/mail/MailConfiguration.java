package pl.edu.pja.prz.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@ComponentScan
@PropertySource("classpath:application-mail.properties")
@EnableAsync
public class MailConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(MailConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("Loaded module: MAIL");
    }

    @Bean
    public Context createThymeleafContext() {
        return new Context(Locale.ENGLISH);
    }

}
