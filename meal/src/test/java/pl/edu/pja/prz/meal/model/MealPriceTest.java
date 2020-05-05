package pl.edu.pja.prz.meal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MealPriceTest {
    private MealPrice mealPrice;
    private MealPrice mealPrice2;

    @BeforeEach
    public void setUp() {
        mealPrice = new MealPrice();
        mealPrice2 = new MealPrice();

        mealPrice.setMealPrice(new BigDecimal(1));
        mealPrice2.setMealPrice(new BigDecimal(1));
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int code1 = mealPrice.hashCode();
        int code2 = mealPrice2.hashCode();

        //Then
        assertEquals(code1, code2);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_ObjectsAreDifferent() {
        //Given

        //When
        mealPrice.setMealPrice(new BigDecimal(2));

        int code1 = mealPrice.hashCode();
        int code2 = mealPrice2.hashCode();

        //Then
        assertNotEquals(code1, code2);
    }

    @Test

    public void Should_ReturnTrue_When_ObjectsAreEqual() {
        //Given

        //When
        boolean firstToSecond = mealPrice.equals(mealPrice2);
        boolean secondToFirst = mealPrice2.equals(mealPrice);

        //Then
        assertTrue(firstToSecond);
        assertTrue(secondToFirst);
    }
}