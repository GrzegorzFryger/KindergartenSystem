package pl.edu.pja.prz.groups.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Child {
    @Id
    private UUID id;
    @ManyToMany(mappedBy = "children")
    private List<Group> groups;

    public Child() {
    }

    public Child(List<Group> groups) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id.equals(child.id) &&
                Objects.equals(groups, child.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groups);
    }
}