package pl.edu.pja.prz.groups.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@org.hibernate.annotations.NamedNativeQueries(
		@org.hibernate.annotations.NamedNativeQuery(name = "Group_getGroupsForChild",
				query = "SELECT g.id, g.groupDescription, g.groupName\n" +
						"FROM classrooms.classroom as g\n" +
						"INNER JOIN classrooms.classroom_child as gc\n" +
						"\tON g.id = gc.group_id\n" +
						"INNER JOIN classrooms.child as c\n" +
						"\tON gc.child_id = c.id\n" +
						"WHERE c.id = :id", resultClass = Group.class)
)
@Entity
@Table(name = "classroom", schema = "classrooms")
public class Group extends BaseEntityLong {
	private String groupName;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "classroom_child",
			joinColumns = {@JoinColumn(name = "group_id")},
			inverseJoinColumns = {@JoinColumn(name = "child_id")})
	private Set<Child> children = new HashSet<>();
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

	public boolean addChild(Child child) {
		return children.add(child);
	}

	public boolean removeChild(Child child) {
		return children.remove(child);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Group group = (Group) o;
		return groupName.equals(group.groupName) &&
				children.equals(group.children) &&
				groupDescription.equals(group.groupDescription);
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
