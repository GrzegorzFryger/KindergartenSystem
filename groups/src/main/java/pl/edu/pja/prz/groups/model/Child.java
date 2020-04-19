package pl.edu.pja.prz.groups.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Child {
	@Id
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID id;
	@ManyToMany(mappedBy = "children")
	private List<Group> groups;

	public Child() {
	}

	public Child(UUID id, List<Group> groups) {
		this.id = id;
		this.groups = groups;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "Child{" +
				"id=" + id +
				", groups=" + groups +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Child child = (Child) o;
		return Objects.equals(id, child.id) &&
				Objects.equals(groups, child.groups);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, groups);
	}
}
