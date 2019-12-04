package pl.edu.pja.prz.meal.exception;

public class MealPriceListAlreadyExistException extends Exception {
    private String message;

    public MealPriceListAlreadyExistException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
