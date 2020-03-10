package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.commons.model.Phone;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guardian extends Account {
	@ManyToMany
	@JoinTable(name = "guardian_child",
			joinColumns = {@JoinColumn(name = "fk_guardian")},
			inverseJoinColumns = {@JoinColumn(name = "fk_child")})
	private Set<Child> children = new HashSet<>();

	public Guardian() {
		super();
	}

	public Guardian(Address address, FullName fullName, Phone phoneNumber, Password password, String email) {
		super(address, fullName, phoneNumber, password, email);
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public boolean addChild(Child child) {
		child.getGuardians().add(this);
		return this.children.add(child);
	}

	public boolean removeChild(Child child) {
		child.getGuardians().remove(this);
		return this.children.add(child);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Guardian)) return false;
		if (!super.equals(o)) return false;

		Guardian guardian = (Guardian) o;

		return getChildren() != null ? getChildren().equals(guardian.getChildren()) : guardian.getChildren() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getChildren() != null ? getChildren().hashCode() : 0);
		return result;
	}
}
