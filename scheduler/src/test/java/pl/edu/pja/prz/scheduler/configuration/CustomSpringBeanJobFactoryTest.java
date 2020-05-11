package pl.edu.pja.prz.scheduler.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobDetail;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.spi.OperableTrigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomSpringBeanJobFactoryTest {

    @Mock
    private ApplicationContext applicationContext;

    private CustomSpringBeanJobFactory customSpringBeanJobFactory;

    @BeforeEach
    public void setUp() {
        customSpringBeanJobFactory = new CustomSpringBeanJobFactory();
    }

    @Test
    public void Should_SetApplicationContext() {
        //Given

        //When
        customSpringBeanJobFactory.setApplicationContext(applicationContext);

        //Then
        verify(applicationContext, only()).getAutowireCapableBeanFactory();
    }

    @Test
    public void Should_ThrowExceptionWhenContextIsNull() throws Exception {
        //Given
        JobDetail jobDetail = new JobDetailImpl();
        OperableTrigger operableTrigger = new CronTriggerImpl();
        TriggerFiredBundle triggerFiredBundle = new TriggerFiredBundle(
                jobDetail,
                operableTrigger,
                new CronCalendar("0 0 12 * * ? 2020"), // At 12:00 pm every day during the year 2020
                true,
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                Date.from(Instant.now())
        );

        //When
        assertThrows(Exception.class, () -> {
            customSpringBeanJobFactory.createJobInstance(triggerFiredBundle);
        });


        //Then
    }
}