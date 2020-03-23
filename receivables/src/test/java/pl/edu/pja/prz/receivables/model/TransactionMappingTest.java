package pl.edu.pja.prz.receivables.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMappingTest {
    private TransactionMapping transactionMapping;
    private TransactionMapping transactionMapping_2;

    @BeforeEach
    public void setUp() {
        UUID childId = UUID.randomUUID();
        UUID guardianId = UUID.randomUUID();

        transactionMapping = new TransactionMapping();
        transactionMapping.setTitle("XYZ-123456");
        transactionMapping.setChildId(childId);
        transactionMapping.setGuardianId(guardianId);

        transactionMapping_2 = new TransactionMapping();
        transactionMapping_2.setTitle("XYZ-123456");
        transactionMapping_2.setChildId(childId);
        transactionMapping_2.setGuardianId(guardianId);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = transactionMapping.hashCode();

        //When
        transactionMapping.setTitle("SOME NEW TITLE");
        int afterChange = transactionMapping.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = transactionMapping.hashCode();
        int second = transactionMapping_2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = transactionMapping.equals(transactionMapping_2);
        boolean secondEqualsToFirst = transactionMapping_2.equals(transactionMapping);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

}
