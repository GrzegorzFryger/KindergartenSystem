package pl.edu.pja.prz.scheduler.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.DelegatingJob;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class QuartzFactoryTest {

    @Mock
    IdentifyGenerator identifyGenerator;

    @Mock
    ApplicationContext applicationContext;

    private QuartzFactory quartzFactory;

    @BeforeEach
    public void setUp() {
        quartzFactory = new QuartzFactory(identifyGenerator, applicationContext);
    }

    @Test
    public void Should_CreateFactory() {
        //Given

        //When

        //Then
        assertNotNull(quartzFactory);
    }

    @Test
    public void Should_CreateJobDetail() {
        //Given

        //When
        Optional<JobDetail> result = quartzFactory.createJobDetail(
                DelegatingJob.class,
                "Some description",
                true,
                "Some group",
                new HashMap<>()
        );

        //Then
        assertTrue(result.isPresent());
    }

    @Test
    public void Should_CreateCronTrigger() {
        //Given

        //When
        Optional<CronTrigger> result = quartzFactory.createCronTrigger(
                "Some description",
                "0 0 12 * * ? 2020", // At 12:00 pm every day during the year 2020
                "Some group"
        );

        //Then
        assertTrue(result.isPresent());
    }

    @Test
    public void Should_CreateSimpleTrigger() {
        //Given

        //When
        Optional<SimpleTrigger> result = quartzFactory.createSimpleTrigger(
                "Some description",
                LocalDateTime.now(),
                1,
                "Some group"
        );

        //Then
        assertTrue(result.isPresent());
    }
}