package pl.edu.pja.prz.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GuardianChildDependencyTest {
    private GuardianChildDependency guardianChildDependency;
    private GuardianChildDependency guardianChildDependency2;

    private UUID childId;
    private UUID guardianId;
    private FullName childFullName;

    @BeforeEach
    public void setUp() {
        childId = UUID.randomUUID();
        guardianId = UUID.randomUUID();
        childFullName = new FullName("Paweł", "Jumper");

        guardianChildDependency = new GuardianChildDependency();
        guardianChildDependency.setChildId(childId);
        guardianChildDependency.setGuardianId(guardianId);
        guardianChildDependency.setChildFullName(childFullName);

        guardianChildDependency2 = new GuardianChildDependency(guardianId, childId, childFullName);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = guardianChildDependency.hashCode();

        //When
        guardianChildDependency.setChildId(UUID.randomUUID());
        int afterChange = guardianChildDependency.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = guardianChildDependency.hashCode();
        int second = guardianChildDependency2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = guardianChildDependency.equals(guardianChildDependency2);
        boolean secondEqualsToFirst = guardianChildDependency2.equals(guardianChildDependency);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnCorrectString() {
        //Given
        String expectedResult = "GuardianChildDependency{" +
                "guardianId=" + guardianId +
                ", childId=" + childId +
                ", childFullName=" + childFullName +
                '}';

        //When
        String result = guardianChildDependency.toString();

        //Then
        assertEquals(expectedResult, result);
    }
}
