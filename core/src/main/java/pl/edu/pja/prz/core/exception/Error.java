package pl.edu.pja.prz.core.exception;

public class Error {
    private final String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
