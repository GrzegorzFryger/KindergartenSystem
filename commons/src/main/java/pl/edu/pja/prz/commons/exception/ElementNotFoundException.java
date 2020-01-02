package pl.edu.pja.prz.commons.exception;

public class ElementNotFoundException extends RuntimeException {
    private final String message;

    public ElementNotFoundException(String element, Long id) {
        message = buildErrorMessage(element, String.valueOf(id));
    }

    public ElementNotFoundException(String element, String id) {
        message = buildErrorMessage(element, id);
    }

    public ElementNotFoundException(Long id) {
        message = buildErrorMessage("Element", String.valueOf(id));
    }

    private String buildErrorMessage(String element, String id) {
        return "[ERROR] " + element + " with id: " + id + " not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
