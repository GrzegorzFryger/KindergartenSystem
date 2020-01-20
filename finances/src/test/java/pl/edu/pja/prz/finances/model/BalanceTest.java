package pl.edu.pja.prz.finances.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BalanceTest {
    private Balance balance;
    private Balance balance2;

    @BeforeEach
    public void setUp() {
        UUID childId = UUID.randomUUID();
        UUID guardianId = UUID.randomUUID();

        balance = new Balance();
        balance.setAmount(new BigDecimal("50.00"));
        balance.setChildId(childId);
        balance.setGuardianId(guardianId);

        balance2 = new Balance();
        balance2.setAmount(new BigDecimal("50.00"));
        balance2.setChildId(childId);
        balance2.setGuardianId(guardianId);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = balance.hashCode();

        //When
        balance.setAmount(new BigDecimal("25.00"));
        int afterChange = balance.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = balance.hashCode();
        int second = balance2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = balance.equals(balance2);
        boolean secondEqualsToFirst = balance2.equals(balance);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}