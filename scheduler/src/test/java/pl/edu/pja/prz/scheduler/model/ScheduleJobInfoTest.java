package pl.edu.pja.prz.scheduler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleJobInfoTest {
    private ScheduleJobInfo scheduleJobInfo;
    private ScheduleJobInfo scheduleJobInfo2;

    @BeforeEach
    public void setUp() {
        scheduleJobInfo = new ScheduleJobInfo();
        scheduleJobInfo2 = new ScheduleJobInfo();

        scheduleJobInfo = new ScheduleJobInfo();
        scheduleJobInfo.setJobKey("test job key");
        scheduleJobInfo.setJobDescription("test job description");
        scheduleJobInfo.setTriggerDescription("test trigger description");
        scheduleJobInfo.setIsDurable("durable");
        scheduleJobInfo.setTriggerKey("test trigger key");
        scheduleJobInfo.setTriggerStart(new Date(1584190894474L)); //Something around 14.03.2020
        scheduleJobInfo.setTriggerEnd(new Date(1584190952707L)); //Something around 14.03.2020

        scheduleJobInfo2 = new ScheduleJobInfo(
                "test job key",
                "test job description",
                "durable",
                "test trigger key",
                "test trigger description",
                new Date(1584190894474L),
                new Date(1584190952707L)
        );
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = scheduleJobInfo.hashCode();

        //When
        scheduleJobInfo.setJobDescription("New Description");
        int afterChange = scheduleJobInfo.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = scheduleJobInfo.hashCode();
        int second = scheduleJobInfo2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = scheduleJobInfo.equals(scheduleJobInfo2);
        boolean secondEqualsToFirst = scheduleJobInfo2.equals(scheduleJobInfo);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}