package pl.edu.pja.prz.commons.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    public void Should_ReturnProperMessage() {
        //Given
        BusinessException exception = new BusinessException("some reason");

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Application error occurred. Reason: some reason", result);
    }
}