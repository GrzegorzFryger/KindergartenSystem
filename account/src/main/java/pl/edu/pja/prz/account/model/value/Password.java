package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Password {
	private LocalDate createDate;
	private String password;

	public Password() {
	}

	public Password(String password) {
		this.password = password;
		this.createDate = LocalDate.now();
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public String getPassword() {
		return password;
	}

}
