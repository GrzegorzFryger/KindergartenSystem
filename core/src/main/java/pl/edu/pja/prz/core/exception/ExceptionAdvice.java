package pl.edu.pja.prz.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.exception.IncorrectInputException;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({
            BusinessException.class,
            IncorrectInputException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(Exception e) {
        logger.debug(e.getMessage());
        return new Error(e.getMessage());
    }

    @ExceptionHandler({
            ElementNotFoundException.class,
            NullPointerException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleErrorWhenResourceNotFound(Exception e) {
        logger.debug(e.getMessage());
        return new Error(e.getMessage());
    }
}
