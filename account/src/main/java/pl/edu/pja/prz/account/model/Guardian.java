package pl.edu.pja.prz.account.model;

import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.Phone;

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


}
