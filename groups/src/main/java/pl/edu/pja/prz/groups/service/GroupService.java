package pl.edu.pja.prz.groups.service;

import pl.edu.pja.prz.groups.model.Group;

import java.util.List;

public interface GroupService {

    Group createGroup(Group group);

    Group updateGroup(Group group, Long groupToUpdateId);

    void deleteGroup(Long id);

    Group getGroup(Long id);

    List<Group> getAllGroups();

}
