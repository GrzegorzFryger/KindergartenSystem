package pl.edu.pja.prz.commons.exception;

/**
 * Custom Exception, which is handled by Exception Advice from core module.<br><br>
 *
 * This exception should be used in all cases, where some field from user input is not available
 * (for instance: when it's null)
 */
public class EmptyInputException extends RuntimeException {
    private final String message;

    public EmptyInputException(String expectedType) {
        this.message = buildErrorMessage(expectedType);
    }

    public EmptyInputException(String expectedType, String fieldName) {
        this.message = buildErrorMessage(expectedType, fieldName);
    }

    private String buildErrorMessage(String expectedType) {
        return "Empty input! Excepted type: " + expectedType + ", but found: empty element";
    }

    private String buildErrorMessage(String expectedType, String fieldName) {
        return "Empty input! Excepted field: [" + expectedType + "] " + fieldName + ", but found: empty element";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
