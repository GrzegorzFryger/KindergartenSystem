package pl.edu.pja.prz.groups.Service;

import pl.edu.pja.prz.groups.Model.Group;

import java.util.List;

public interface GroupService {

    Group createGroup(Group group);
    Group editGroup(Group group);
    Group updateGroup(Group group);
    void deleteGroup(Long id);
    List<Group> getAllGroups();

}
