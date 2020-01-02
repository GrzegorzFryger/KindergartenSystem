package pl.edu.pja.prz.meal.exception;

public class MealActivityStatusException extends Exception {

	private final String message;
	public MealActivityStatusException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
