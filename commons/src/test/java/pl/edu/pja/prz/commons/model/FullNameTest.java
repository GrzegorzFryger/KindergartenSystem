package pl.edu.pja.prz.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {
    private FullName fullName;
    private FullName fullName2;

    @BeforeEach
    public void setUp() {
        fullName = new FullName();
        fullName.setName("Paweł");
        fullName.setSurname("Jumper");

        fullName2 = new FullName("Paweł", "Jumper");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = fullName.hashCode();

        //When
        fullName.setSurname("Pawlewicz");
        int afterChange = fullName.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = fullName.hashCode();
        int second = fullName2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = fullName.equals(fullName2);
        boolean secondEqualsToFirst = fullName2.equals(fullName);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnCorrectString() {
        //Given
        String expectedResult = "FullName{name='Paweł', surname='Jumper'}";

        //When
        String result = fullName.toString();

        //Then
        assertEquals(expectedResult, result);
    }
}
