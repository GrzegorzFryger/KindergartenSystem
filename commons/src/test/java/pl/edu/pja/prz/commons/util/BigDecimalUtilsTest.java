package pl.edu.pja.prz.commons.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalUtilsTest {

    @Test
    public void Should_ReturnFalse_When_NumberIsNegative_ForIsPositiveMethod() {
        //Given
        BigDecimal number = new BigDecimal("-0.01");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_NumberIsZero_ForIsPositiveMethod() {
        //Given
        BigDecimal number = new BigDecimal("0.00");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnTrue_When_NumberIsPositive_ForIsPositiveMethod() {
        //Given
        BigDecimal number = new BigDecimal("0.01");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertTrue(result);
    }

    @Test
    public void Should_ReturnTrue_When_NumberIsNegative_ForIsNegativeMethod() {
        //Given
        BigDecimal number = new BigDecimal("-0.01");

        //When
        boolean result = BigDecimalUtils.isNegative(number);

        //Then
        assertTrue(result);
    }

    @Test
    public void Should_ReturnFalse_When_NumberIsZero_ForIsNegativeMethod() {
        //Given
        BigDecimal number = new BigDecimal("0.00");

        //When
        boolean result = BigDecimalUtils.isNegative(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_NumberIsPositive_ForIsNegativeMethod() {
        //Given
        BigDecimal number = new BigDecimal("0.01");

        //When
        boolean result = BigDecimalUtils.isNegative(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_SumTwoNumbers() {
        //Given
        BigDecimal a = new BigDecimal("20.50");
        BigDecimal b = new BigDecimal("66.33");

        //When
        BigDecimal result = BigDecimalUtils.sum(a, b);

        //Then
        assertEquals(new BigDecimal("86.83"), result);
    }

    @Test
    public void Should_SubtractTwoNumbers() {
        //Given
        BigDecimal a = new BigDecimal("20.50");
        BigDecimal b = new BigDecimal("66.33");

        //When
        BigDecimal result = BigDecimalUtils.subtract(a, b);

        //Then
        assertEquals(new BigDecimal("-45.83"), result);
    }
}