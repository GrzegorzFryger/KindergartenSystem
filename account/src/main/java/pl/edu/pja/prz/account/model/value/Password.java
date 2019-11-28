package pl.edu.pja.prz.account.model.value;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Password)) return false;

		Password password = (Password) o;

		if (getCreateDate() != null ? !getCreateDate().equals(password.getCreateDate()) : password.getCreateDate() != null)
			return false;
		if (getOldPassword() != null ? !getOldPassword().equals(password.getOldPassword()) : password.getOldPassword() != null)
			return false;
		return getCurrentPassword() != null ? getCurrentPassword().equals(password.getCurrentPassword()) : password.getCurrentPassword() == null;
	}

	@Override
	public int hashCode() {
		int result = getCreateDate() != null ? getCreateDate().hashCode() : 0;
		result = 31 * result + (getOldPassword() != null ? getOldPassword().hashCode() : 0);
		result = 31 * result + (getCurrentPassword() != null ? getCurrentPassword().hashCode() : 0);
		return result;
	}
}
