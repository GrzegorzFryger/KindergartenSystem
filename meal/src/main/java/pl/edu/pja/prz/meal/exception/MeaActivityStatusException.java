package pl.edu.pja.prz.meal.exception;

public class MeaActivityStatusException extends Exception {

	private String message;

	public MeaActivityStatusException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
