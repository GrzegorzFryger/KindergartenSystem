package pl.edu.pja.prz.groups.model;

import java.util.HashSet;
import java.util.Set;

public class GroupBuilder {
	private String groupName;
	private Set<Child> children = new HashSet<>();
	private String groupDescription;

	public GroupBuilder withGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public GroupBuilder withChildren(Set<Child> children) {
		this.children = children;
		return this;
	}

	public GroupBuilder withGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
		return this;
	}

	public Group build() {
		Group group = new Group();
		group.setGroupName(groupName);
		group.setChildren(children);
		group.setGroupDescription(groupDescription);
		return group;
	}
}
