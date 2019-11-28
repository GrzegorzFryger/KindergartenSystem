package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
public class Password {

	private LocalDate createDate;
	private String oldPassword;
	private String currentPassword;

	public Password() {
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
}
