package pl.edu.pja.prz.groups.service;

import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GroupService {

	Group createGroup(Group group);

	Group updateGroup(Group group);

	void deleteGroup(Long id);

	Group getGroup(Long id);

	List<Group> getAllGroups();

	Group addChildToGroup(Long groupId, Child child);

	Group removeChildFromGroup(Long groupId, Child child);

	Set<Child> findAllChildrenInGroup(Long groupId);

}
