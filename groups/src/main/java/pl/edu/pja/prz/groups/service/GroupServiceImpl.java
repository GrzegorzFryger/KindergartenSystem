package pl.edu.pja.prz.groups.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
	private static final String GROUP = "Group";

	private final GroupRepository groupRepository;

	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Group createGroup(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public Group updateGroup(Group group) {
		if (groupRepository.findById(group.getId()).isEmpty()) {
			throw new ElementNotFoundException(GROUP, group.getId());
		}
		Group groupToUpdate = getGroup(group.getId());
		if (groupToUpdate.getGroupName() != null) {
			groupToUpdate.setGroupName(group.getGroupName());
		}
		if (groupToUpdate.getGroupDescription() != null) {
			groupToUpdate.setGroupDescription(group.getGroupDescription());
		}

		return groupRepository.save(groupToUpdate);
	}

	@Override
	public void deleteGroup(Long id) {
		if (groupRepository.findById(id).isEmpty()) {
			throw new ElementNotFoundException(GROUP, id);
		}
		groupRepository.deleteById(id);

	}

	@Override
	public Group getGroup(Long id) {
		return groupRepository.findById(id).orElseThrow(
				() -> new ElementNotFoundException(GROUP, id));
	}

	@Override
	public List<Group> getAllGroups() {
		return groupRepository.findAll();
	}

	@Override
	public Group addChildToGroup(Long groupId, Child child) {
		Group groupToAddChild = groupRepository.findById(groupId).orElseThrow(
				() -> new ElementNotFoundException(GROUP, groupId));
		groupToAddChild.addChild(child);
		return groupRepository.save(groupToAddChild);
	}

	@Override
	public void removeChildFromGroup(Long groupId, UUID childId) {
		Group groupToRemoveChild = groupRepository.findById(groupId).orElseThrow(
				() -> new ElementNotFoundException(GROUP, groupId));

		Child childToRemove = null;

		for (Child child : groupToRemoveChild.getChildren()) {
			if (child.getId().equals(childId)) {
				childToRemove = child;
			}
		}
		if (childToRemove != null) {
			groupToRemoveChild.removeChild(childToRemove);
		}
		groupRepository.save(groupToRemoveChild);
	}

	@Override
	public List<UUID> findIdOfAllChildrenInGroup(Long groupId) {
		Group groupToRead = groupRepository.findById(groupId).orElseThrow(
				() -> new ElementNotFoundException(GROUP, groupId));
		Set<Child> children = groupToRead.getChildren();
		List<UUID> childrenIds = children.stream().map(Child::getId).collect(Collectors.toList());
		return childrenIds;
	}

	@Override
	public List<Group> getGroupsForChild(UUID childId) {
		return groupRepository.getAllGroupsAssignedToChild(childId.toString());
	}
}
