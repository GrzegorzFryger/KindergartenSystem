package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;


@Embeddable
public class Phone {
	private String phone;

	public Phone() {
	}

	public Phone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Phone)) return false;

		Phone phone1 = (Phone) o;

		return getPhone() != null ? getPhone().equals(phone1.getPhone()) : phone1.getPhone() == null;
	}

	@Override
	public int hashCode() {
		return getPhone() != null ? getPhone().hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Phone{" +
				"phone='" + phone + '\'' +
				'}';
	}
}
