package pl.edu.pja.prz.commons.exception;

/**
 * Custom Exception, which is handled by Exception Advice from core module.<br><br>
 *
 * This exception should be used in all cases, where user input is incorrect
 * (for instance: wrong input type, incorrect amount of parameters etc.
 */
public class IncorrectInputException extends RuntimeException {
    private final String message;

    public IncorrectInputException(String expectedType) {
        this.message = buildErrorMessage(expectedType);
    }

    private String buildErrorMessage(String type) {
        return "[ERROR] Wrong input! Excepted type: " + type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
