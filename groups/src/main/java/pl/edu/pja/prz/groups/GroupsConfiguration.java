package pl.edu.pja.prz.groups;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@PropertySource("classpath:application-groups.properties")
public class GroupsConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GroupsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("Loaded module: GROUPS");
    }
}