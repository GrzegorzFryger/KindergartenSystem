package pl.edu.pja.prz.groups.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private List<Child> childrenList;
    private String groupDescription;


    public Group() {

    }

    public Group(String groupName, List<Child> childrenList, String groupDescription) {
        this.groupName = groupName;
        this.childrenList = childrenList;
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

    public List<Child> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Child> childrenList) {
        this.childrenList = childrenList;
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
                ", childrenList=" + childrenList +
                ", groupDescription='" + groupDescription + '\'' +
                '}';
    }
}
