package pl.edu.pja.prz.account.exception;

import pl.edu.pja.prz.commons.model.FullName;

public class MoreThanOneElement extends Exception {
	private final String message;

	public MoreThanOneElement(FullName fullName) {
		this.message = buildErrorMessage(fullName);
	}

	private String buildErrorMessage(FullName fullName) {
		return "Find more than one person with  " +  fullName.getName() + " " + fullName.getSurname();
	}

	@Override
	public String getMessage() {
		return message;
	}

}
