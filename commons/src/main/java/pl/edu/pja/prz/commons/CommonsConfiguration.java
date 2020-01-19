package pl.edu.pja.prz.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-commons.properties")
public class CommonsConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CommonsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("Loaded module: COMMONS");
    }
}
