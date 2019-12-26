package pl.edu.pja.prz.groups.model;

import java.util.List;

public class GroupBuilder {
    private Long id;
    private String groupName;
    private List<Child> children;
    private String groupDescription;

    public GroupBuilder withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupBuilder withChildren(List<Child> children) {
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
