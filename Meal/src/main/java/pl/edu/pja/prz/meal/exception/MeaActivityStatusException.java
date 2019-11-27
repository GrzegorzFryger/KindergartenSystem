package pl.edu.pja.prz.meal.exception;

public class MealAlreadyActiveException extends Exception {

    private String message;

    public MealAlreadyActiveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
