package pl.edu.pja.prz.groups.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "classroom", schema = "classrooms")
public class Group extends BaseEntityLong {
	private String groupName;
	@ManyToMany
	@JoinTable(name = "classroom_child",
			joinColumns = {@JoinColumn(name = "group_id")},
			inverseJoinColumns = {@JoinColumn(name = "child_id")})
	private Set<Child> children;
	private String groupDescription;


	public Group() {

	}

	public Group(String groupName, Set<Child> children, String groupDescription) {
		this.groupName = groupName;
		this.children = children;
		this.groupDescription = groupDescription;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Group group = (Group) o;
		return Objects.equals(groupName, group.groupName) &&
				Objects.equals(children, group.children) &&
				Objects.equals(groupDescription, group.groupDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), groupName, children, groupDescription);
	}

	@Override
	public String toString() {
		return "Group{" +
				"groupName='" + groupName + '\'' +
				", children=" + children +
				", groupDescription='" + groupDescription + '\'' +
				'}';
	}
}