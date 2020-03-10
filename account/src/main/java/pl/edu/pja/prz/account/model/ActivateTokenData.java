package pl.edu.pja.prz.account.model;

public class ActivateTokenData {
	private String email;
	private String hasPassword;

	public ActivateTokenData(String email, String hasPassword) {
		this.email = email;
		this.hasPassword = hasPassword;
	}

	public String getEmail() {
		return email;
	}

	public String getHasPassword() {
		return hasPassword;
	}
}
