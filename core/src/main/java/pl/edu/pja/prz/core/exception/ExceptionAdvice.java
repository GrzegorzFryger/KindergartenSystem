package pl.edu.pja.prz.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.commons.exception.IncorrectInputTypeException;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    private static final String EXCEPTION_WAS_THROWN = "Exception was thrown. Responding with: ";

    @ExceptionHandler({
            BusinessException.class,
            IncorrectInputTypeException.class,
            AccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(Exception e) {
        logger.debug(EXCEPTION_WAS_THROWN + HttpStatus.BAD_REQUEST, e);
        return new Error(e.getMessage());
    }

    @ExceptionHandler({
            ElementNotFoundException.class,
            EmptyInputException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleErrorWhenResourceNotFound(Exception e) {
        logger.debug(EXCEPTION_WAS_THROWN + HttpStatus.NOT_FOUND, e);
        return new Error(e.getMessage());
    }

    /**
     * Catch all for any other exceptions...
     */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleAnyException(Exception e) {
        logger.error(EXCEPTION_WAS_THROWN + HttpStatus.INTERNAL_SERVER_ERROR, e);
        return new Error(e.getMessage());
    }
}
