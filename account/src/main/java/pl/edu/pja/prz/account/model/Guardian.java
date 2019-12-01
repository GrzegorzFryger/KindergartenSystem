package pl.edu.pja.prz.account.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Guardian extends Account {

	@ManyToMany
	@JoinTable(name = "guardian_child",
			joinColumns = {@JoinColumn(name = "fk_guardian")},
			inverseJoinColumns = {@JoinColumn(name = "fk_child")})
	private Set<Child> children;

	public Guardian() {
		super();
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
