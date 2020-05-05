package pl.edu.pja.prz.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private Address address;
    private Address address2;

    @BeforeEach
    public void setUp() {
        address = new Address();
        address.setCity("City");
        address.setPostalCode("12-500");
        address.setStreetNumber("Słoneczna 1000");

        address2 = new Address("12-500", "City", "Słoneczna 1000");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = address.hashCode();

        //When
        address.setStreetNumber("Wrocławska 500");
        int afterChange = address.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = address.hashCode();
        int second = address2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = address.equals(address2);
        boolean secondEqualsToFirst = address2.equals(address);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnCorrectString() {
        //Given
        String expected = "Address{postalCode='12-500', city='City', streetNumber='Słoneczna 1000'}";

        //When
        String result = address.toString();

        //Then
        assertEquals(expected, result);
    }
}
