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

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Password)) return false;

		Password password1 = (Password) o;

		if (getCreateDate() != null ? !getCreateDate().equals(password1.getCreateDate()) : password1.getCreateDate() != null)
			return false;
		return getPassword() != null ? getPassword().equals(password1.getPassword()) : password1.getPassword() == null;
	}

	@Override
	public int hashCode() {
		int result = getCreateDate() != null ? getCreateDate().hashCode() : 0;
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Password{" +
				"createDate=" + createDate +
				", password='" + password + '\'' +
				'}';
	}
}
