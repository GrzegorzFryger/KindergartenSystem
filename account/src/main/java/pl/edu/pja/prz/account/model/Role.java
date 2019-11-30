package pl.edu.pja.prz.account.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
public class Role extends BaseEntity<Long> {

	@Transient
	private final String ROLEWORD = "ROLE_";
	private String name;
	@ManyToMany
	@JoinTable(name = "role_author",
			joinColumns = {@JoinColumn(name = "fk_role")},
			inverseJoinColumns = {@JoinColumn(name = "fk_privilege")})
	private Set<Privilege> privileges;

	@ManyToMany
	@JoinTable(name = "role_account",
			joinColumns = {@JoinColumn(name = "fk_role")},
			inverseJoinColumns = {@JoinColumn(name = "fk_account")})
	private Set<Account> accounts;

	protected Role() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = ROLEWORD + name.toUpperCase();
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public boolean addPrivilege(Privilege privilege) {
		privilege.getRoles().add(this);
		return this.privileges.add(privilege);
	}

	public boolean removePrivilege(Privilege privilege) {
		privilege.getRoles().remove(this);
		return getPrivileges().remove(privilege);
	}

	public boolean hasPrivileges(Privilege... privileges) {
		return (privileges.length > 0) && this.privileges.containsAll(Arrays.asList(privileges));
	}

}
