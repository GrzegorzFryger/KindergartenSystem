package pl.edu.pja.prz.finances.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountNumberTest {

    private AccountNumber accountNumber;
    private AccountNumber accountNumber2;

    @BeforeEach
    public void setUp() {
        accountNumber = new AccountNumber();
        accountNumber2 = new AccountNumber();

        accountNumber.setId(1L);
        accountNumber2.setId(1L);

        accountNumber.setAccountNumber("1234");
        accountNumber2.setAccountNumber("1234");

        accountNumber.setStreet("Street");
        accountNumber2.setStreet("Street");

        accountNumber.setCity("City");
        accountNumber2.setCity("City");

        accountNumber.setPostalCode("10-500");
        accountNumber2.setPostalCode("10-500");
    }


    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = accountNumber.hashCode();

        //When
        accountNumber.setAccountNumber("New account number");
        int afterChange = accountNumber.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = accountNumber.hashCode();
        int second = accountNumber2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = accountNumber.equals(accountNumber2);
        boolean secondEqualsToFirst = accountNumber2.equals(accountNumber);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnCorrectString() {
        //Given
        String expected = "AccountNumber [1234]";

        //When
        String result = accountNumber.toString();

        //Then
        assertEquals(expected, result);
    }
}
