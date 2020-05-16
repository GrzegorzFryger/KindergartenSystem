package pl.edu.pja.prz.meal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealConfigurationTest {

    private MealConfiguration configuration;
    private MealConfiguration configuration2;

    @BeforeEach
    public void setUp() {
        configuration = new MealConfiguration();
        configuration2 = new MealConfiguration();

        configuration.setEmailToSendMealOrder("aaa@wp.pl");
        configuration2.setEmailToSendMealOrder("aaa@wp.pl");
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int code1 = configuration.hashCode();
        int code2 = configuration2.hashCode();

        //Then
        assertEquals(code1, code2);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_ObjectsAreDifferent() {
        //Given

        //When
        configuration.setEmailToSendMealOrder("some new email");

        int code1 = configuration.hashCode();
        int code2 = configuration2.hashCode();

        //Then
        assertNotEquals(code1, code2);
    }

    @Test

    public void Should_ReturnTrue_When_ObjectsAreEqual() {
        //Given

        //When
        boolean firstToSecond = configuration.equals(configuration2);
        boolean secondToFirst = configuration2.equals(configuration);

        //Then
        assertTrue(firstToSecond);
        assertTrue(secondToFirst);
    }

}