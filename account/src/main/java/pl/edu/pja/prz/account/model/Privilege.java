package pl.edu.pja.prz.account.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;

@Entity
public class Privilege extends BaseEntity<Long> {

	@Transient
	private final String PRIVILEGEWORD = "_PRIVILEGE";
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Set<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase() + PRIVILEGEWORD;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

}
