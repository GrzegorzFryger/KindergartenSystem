package pl.edu.pja.prz.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({NullPointerException.class, ElementNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(Exception e) {
        return new Error(e.getMessage());
    }
}
