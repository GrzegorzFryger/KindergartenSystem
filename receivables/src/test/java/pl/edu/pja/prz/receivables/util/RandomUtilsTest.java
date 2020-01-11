package pl.edu.pja.prz.receivables.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomUtilsTest {
    @Test
    public void Should_CreateNumericStringWithProperLength() {
        //Given

        //When
        String result = RandomUtils.randomNumeric(10);

        //Then
        assertEquals(10, result.length());
    }
}