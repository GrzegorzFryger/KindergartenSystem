package pl.edu.pja.prz.meal.exception;

public class MealPriceListAlreadyExistException extends Exception {
	private final String message;
	public MealPriceListAlreadyExistException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
