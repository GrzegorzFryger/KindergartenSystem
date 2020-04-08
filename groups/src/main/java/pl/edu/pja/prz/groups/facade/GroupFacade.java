package pl.edu.pja.prz.groups.facade;

import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GroupFacade {

	GroupDto createGroup(GroupDto groupDto);

	GroupDto updateGroup(GroupDto groupDto);

	void deleteGroup(Long id);

	GroupDto getGroup(Long id);

	List<GroupDto> getAllGroups();

	GroupDto addChildToGroup(Long groupId, UUID childId);

	void removeChildFromGroup(Long groupId, UUID childId);

	Set<ChildDto> findAllChildrenInGroup(Long groupId);

}
