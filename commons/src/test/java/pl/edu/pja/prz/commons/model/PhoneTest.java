package pl.edu.pja.prz.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    private Phone phone;
    private Phone phone2;

    @BeforeEach
    public void setUp() {
        phone = new Phone();
        phone2 = new Phone();

        phone.setPhone("500600700");
        phone2.setPhone("500600700");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = phone.hashCode();

        //When
        phone.setPhone("999000999");
        int afterChange = phone.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = phone.hashCode();
        int second = phone2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = phone.equals(phone2);
        boolean secondEqualsToFirst = phone2.equals(phone);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}