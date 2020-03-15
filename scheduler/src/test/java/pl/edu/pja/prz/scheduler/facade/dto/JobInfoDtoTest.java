package pl.edu.pja.prz.scheduler.facade.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobInfoDtoTest {
    private JobInfoDto jobInfoDto;
    private JobInfoDto jobInfoDto2;

    @BeforeEach
    public void setUp() {
        jobInfoDto = new JobInfoDto();
        jobInfoDto2 = new JobInfoDto();

        jobInfoDto.setDescription("Description");
        jobInfoDto2.setDescription("Description");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = jobInfoDto.hashCode();

        //When
        jobInfoDto.setDescription("New Description");
        int afterChange = jobInfoDto.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = jobInfoDto.hashCode();
        int second = jobInfoDto2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = jobInfoDto.equals(jobInfoDto2);
        boolean secondEqualsToFirst = jobInfoDto2.equals(jobInfoDto);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}
