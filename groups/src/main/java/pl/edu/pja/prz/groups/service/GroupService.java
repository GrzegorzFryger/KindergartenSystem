package pl.edu.pja.prz.groups.service;

import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;

import java.util.List;
import java.util.UUID;

public interface GroupService {

	Group createGroup(Group group);

	Group updateGroup(Group group);

	void deleteGroup(Long id);

	Group getGroup(Long id);

	List<Group> getAllGroups();

	Group addChildToGroup(Long groupId, Child child);

	void removeChildFromGroup(Long groupId, UUID childId);

	List<UUID> findIdOfAllChildrenInGroup(Long groupId);

	List<Group> getGroupsForChild(UUID childId);

}
