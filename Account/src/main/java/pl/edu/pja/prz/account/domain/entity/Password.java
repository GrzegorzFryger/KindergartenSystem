package pl.edu.pja.prz.account.domain.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Password {

	private LocalDate createDate;
	private String oldPassword;
	private String currentPassword;

	public Password(LocalDate createDate, String oldPassword, String currentPassword) {
		this.createDate = createDate;
		this.oldPassword = oldPassword;
		this.currentPassword = currentPassword;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}
}
