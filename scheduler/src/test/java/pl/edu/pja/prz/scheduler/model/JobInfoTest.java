package pl.edu.pja.prz.scheduler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.quartz.DelegatingJob;

import static org.junit.jupiter.api.Assertions.*;

class JobInfoTest {
    private JobInfo jobInfo;
    private JobInfo jobInfo2;

    @BeforeEach
    public void setUp() {
        jobInfo = new JobInfo();
        jobInfo2 = new JobInfo();

        jobInfo.setDescription("Description");
        jobInfo2.setDescription("Description");

        jobInfo.setClassFullName("Full class name");
        jobInfo2.setClassFullName("Full class name");

        jobInfo.setClassType(DelegatingJob.class);
        jobInfo2.setClassType(DelegatingJob.class);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = jobInfo.hashCode();

        //When
        jobInfo.setDescription("New Description");
        int afterChange = jobInfo.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = jobInfo.hashCode();
        int second = jobInfo2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = jobInfo.equals(jobInfo2);
        boolean secondEqualsToFirst = jobInfo2.equals(jobInfo);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}