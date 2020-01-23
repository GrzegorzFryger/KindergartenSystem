package pl.edu.pja.prz.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityUuidTest {
    private BaseEntityUuid entity;
    private BaseEntityUuid entity2;

    @BeforeEach
    public void setUp() {
        entity = new BaseEntityUuid();
        entity2 = new BaseEntityUuid();

        UUID id = UUID.randomUUID();

        entity.setId(id);
        entity2.setId(id);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = entity.hashCode();

        //When
        entity.setId(UUID.randomUUID());
        int afterChange = entity.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = entity.hashCode();
        int second = entity2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = entity.equals(entity2);
        boolean secondEqualsToFirst = entity2.equals(entity);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}