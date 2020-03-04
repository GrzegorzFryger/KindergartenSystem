package pl.edu.pja.prz.account.facade.dto;

public class AccountActivateDto {
	private String token;
	private String rawPassword;
	private String repeatRawPassword;

	public AccountActivateDto() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getRepeatRawPassword() {
		return repeatRawPassword;
	}

	public void setRepeatRawPassword(String repeatRawPassword) {
		this.repeatRawPassword = repeatRawPassword;
	}
}
