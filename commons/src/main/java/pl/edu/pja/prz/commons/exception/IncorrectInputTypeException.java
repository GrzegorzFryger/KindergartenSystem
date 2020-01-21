package pl.edu.pja.prz.commons.exception;

/**
 * Custom Exception, which is handled by Exception Advice from core module.<br><br>
 *
 * This exception should be used in all cases, where user input type is incorrect
 */
public class IncorrectInputTypeException extends RuntimeException {
    private final String message;

    public IncorrectInputTypeException(String expectedType) {
        this.message = buildErrorMessage(expectedType);
    }

    public IncorrectInputTypeException(String expectedType, String actualType) {
        this.message = buildErrorMessage(expectedType, actualType);
    }

    private String buildErrorMessage(String expectedType) {
        return "Wrong input! Excepted type: " + expectedType;
    }

    private String buildErrorMessage(String expectedType, String actualType) {
        return "Wrong input! Excepted type: " + expectedType + ", actual type: " + actualType;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
