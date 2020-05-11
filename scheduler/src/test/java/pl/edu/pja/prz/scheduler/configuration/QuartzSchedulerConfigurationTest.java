package pl.edu.pja.prz.scheduler.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import pl.edu.pja.prz.scheduler.service.JobService;

import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class QuartzSchedulerConfigurationTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private DataSource dataSource;

    private QuartzSchedulerConfiguration quartzSchedulerConfiguration;

    @BeforeEach
    public void setUp() {
        quartzSchedulerConfiguration = new QuartzSchedulerConfiguration();
    }

    @Test
    public void Should_CreateJobService() {
        //Given

        //When
        JobService result = quartzSchedulerConfiguration.createJobService();

        //Then
        assertNotNull(result);
    }

    @Test
    public void Should_CreateQuartzProperties() {
        //Given
        QuartzProperties quartzProperties = new QuartzProperties();
        quartzProperties.getProperties().put("Test property", "Test value");

        //When
        Properties result = quartzSchedulerConfiguration.createQuartzProperties(quartzProperties);

        //Then
        assertNotNull(result);
        assertEquals("Test value", result.getProperty("Test property"));
    }

    @Test
    public void Should_CreateSchedulerFactory() {
        //Given
        Properties properties = new Properties();

        //When
        SchedulerFactoryBean result = quartzSchedulerConfiguration
                .createSchedulerFactory(applicationContext, properties, dataSource);

        //Then
        assertNotNull(result);
    }

}