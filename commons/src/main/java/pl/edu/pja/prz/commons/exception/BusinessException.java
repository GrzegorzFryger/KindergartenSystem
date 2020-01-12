package pl.edu.pja.prz.commons.exception;

/**
 * Custom Exception, which is handled by Exception Advice from core module.<br><br>
 *
 * This exception can be used in cases not handled by other exceptions from this package
 * (for instance: element, which we try to create already exists)
 */
public class BusinessException extends RuntimeException {
    private final String message;

    public BusinessException(String reason) {
        this.message = buildErrorMessage(reason);
    }

    private String buildErrorMessage(String message) {
        return "Application error occurred. Reason: " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
