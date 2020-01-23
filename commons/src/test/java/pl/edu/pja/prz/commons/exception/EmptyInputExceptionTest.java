package pl.edu.pja.prz.commons.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyInputExceptionTest {
    @Test
    public void Should_ReturnProperMessage_When_ExpectedTypeIsProvided() {
        //Given
        String expectedType = "Long";
        EmptyInputException exception = new EmptyInputException(expectedType);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Empty input! Excepted type: Long, but found: empty element", result);
    }

    @Test
    public void Should_ReturnProperMessage_When_ExpectedTypeAndFieldNameIsProvided() {
        //Given
        String expectedType = "Long";
        String fieldName = "amount";
        EmptyInputException exception = new EmptyInputException(expectedType, fieldName);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Empty input! Excepted field: [Long] amount, but found: empty element", result);
    }
}