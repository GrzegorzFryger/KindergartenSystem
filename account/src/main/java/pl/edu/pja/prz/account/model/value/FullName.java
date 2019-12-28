package pl.edu.pja.prz.account.model.value;

import javax.persistence.Embeddable;

@Embeddable
public class FullName {
	private String name;
	private String surname;

	public FullName() {
	}

	public FullName(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FullName)) return false;

		FullName fullName = (FullName) o;

		if (getName() != null ? !getName().equals(fullName.getName()) : fullName.getName() != null) return false;
		return getSurname() != null ? getSurname().equals(fullName.getSurname()) : fullName.getSurname() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FullName{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
