package pl.edu.pja.prz.receivables.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMappingTest {
    private UUID childId;
    private UUID guardianId;

    private TransactionMapping transactionMapping;
    private TransactionMapping transactionMapping2;

    @BeforeEach
    public void setUp() {
        childId = UUID.randomUUID();
        guardianId = UUID.randomUUID();

        transactionMapping = new TransactionMapping();
        transactionMapping.setTitle("XYZ-123456");
        transactionMapping.setChildId(childId);
        transactionMapping.setGuardianId(guardianId);

        transactionMapping2 = new TransactionMapping();
        transactionMapping2.setTitle("XYZ-123456");
        transactionMapping2.setChildId(childId);
        transactionMapping2.setGuardianId(guardianId);
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
        int second = transactionMapping2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = transactionMapping.equals(transactionMapping2);
        boolean secondEqualsToFirst = transactionMapping2.equals(transactionMapping);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnProperString() {
        //Given

        //When
        String result = transactionMapping.toString();

        //Then
        assertEquals(childId + " - XYZ-123456", result);
    }

}
