package pl.edu.pja.prz.meal.exception;

public class NotFoundException extends Exception {

	private final String message;
	public NotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
