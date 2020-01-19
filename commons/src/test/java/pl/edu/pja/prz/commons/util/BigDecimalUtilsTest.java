package pl.edu.pja.prz.commons.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalUtilsTest {

    @Test
    public void Should_ReturnFalse_When_NumberIsNegative() {
        //Given
        BigDecimal number = new BigDecimal("-0.01");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_NumberIsZero() {
        //Given
        BigDecimal number = new BigDecimal("0.00");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnTrue_When_NumberIsPositive() {
        //Given
        BigDecimal number = new BigDecimal("0.01");

        //When
        boolean result = BigDecimalUtils.isPositive(number);

        //Then
        assertTrue(result);
    }
}