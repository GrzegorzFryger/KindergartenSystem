package pl.edu.pja.prz.commons.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectInputTypeExceptionTest {

    @Test
    public void Should_ReturnProperMessage_When_ExpectedTypeIsProvided() {
        //Given
        String expectedType = "Long";
        IncorrectInputTypeException exception = new IncorrectInputTypeException(expectedType);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Wrong input! Excepted type: Long", result);
    }

    @Test
    public void Should_ReturnProperMessage_When_ExpectedAndActualTypeIsProvided() {
        //Given
        String expectedType = "Long";
        String actualType = "String";
        IncorrectInputTypeException exception = new IncorrectInputTypeException(expectedType, actualType);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Wrong input! Excepted type: Long, actual type: String", result);
    }
}