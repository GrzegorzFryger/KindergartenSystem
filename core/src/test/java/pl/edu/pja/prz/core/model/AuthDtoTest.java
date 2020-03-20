package pl.edu.pja.prz.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDtoTest {

    private AuthDto authDto;
    private AuthDto authDto2;

    @BeforeEach
    public void setUp() {
        authDto = new AuthDto();
        authDto2 = new AuthDto();

        authDto.setPassword("PASS");
        authDto2.setPassword("PASS");

        authDto.setUsername("USERNAME");
        authDto2.setUsername("USERNAME");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = authDto.hashCode();

        //When
        authDto.setPassword("SOME NEW PASSWORD");
        int afterChange = authDto.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = authDto.hashCode();
        int second = authDto2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = authDto.equals(authDto2);
        boolean secondEqualsToFirst = authDto2.equals(authDto);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

}
