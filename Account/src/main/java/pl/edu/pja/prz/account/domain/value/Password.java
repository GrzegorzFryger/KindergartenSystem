package pl.edu.pja.prz.account.domain.value;

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

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
}
