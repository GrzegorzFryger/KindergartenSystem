package pl.edu.pja.prz.groups.service;

import pl.edu.pja.prz.groups.model.Group;

import java.util.List;

public interface GroupService {

    Group createGroup(String groupName, String groupDescription);

    Group updateGroup(Group group);

    void deleteGroup(Long id);

    List<Group> getAllGroups();

}
