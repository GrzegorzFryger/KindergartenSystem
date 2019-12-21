package pl.edu.pja.prz.groups.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "classroom", schema = "classrooms")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @ManyToMany
    @JoinTable(name = "classroom_child",
            joinColumns = {@JoinColumn(referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "ID")})
    private List<Child> children;
    private String groupDescription;


    public Group() {

    }

    public Group(String groupName, List<Child> children, String groupDescription) {
        this.groupName = groupName;
        this.children = children;
        this.groupDescription = groupDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", children=" + children +
                ", groupDescription='" + groupDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id.equals(group.id) &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(children, group.children) &&
                Objects.equals(groupDescription, group.groupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, children, groupDescription);
    }
}
