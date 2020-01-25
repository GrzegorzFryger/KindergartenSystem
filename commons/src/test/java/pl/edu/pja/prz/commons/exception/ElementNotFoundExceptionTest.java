package pl.edu.pja.prz.commons.exception;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ElementNotFoundExceptionTest {
    @Test
    public void Should_ReturnProperMessage_When_IdIsLong() {
        //Given
        Long id = 10L;
        ElementNotFoundException exception = new ElementNotFoundException(id);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Element with id: " + id + " not found.", result);
    }

    @Test
    public void Should_ReturnProperMessage_When_ElementIsStringAndIdIsUUID() {
        //Given
        String element = "Some element";
        UUID id = UUID.randomUUID();
        ElementNotFoundException exception = new ElementNotFoundException(element, id);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Some element with id: " + id + " not found.", result);
    }

    @Test
    public void Should_ReturnProperMessage_When_ElementIsStringAndIdIsLong() {
        //Given
        String element = "Some element";
        Long id = 10L;
        ElementNotFoundException exception = new ElementNotFoundException(element, id);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Some element with id: 10 not found.", result);
    }

    @Test
    public void Should_ReturnProperMessage_When_ElementIsStringAndIdIsString() {
        //Given
        String element = "Some element";
        String id = "XYZ-100";
        ElementNotFoundException exception = new ElementNotFoundException(element, id);

        //When
        String result = exception.getMessage();

        //Then
        assertEquals("Some element with id: XYZ-100 not found.", result);
    }
}