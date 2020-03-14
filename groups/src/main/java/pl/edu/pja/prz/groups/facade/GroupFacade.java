package pl.edu.pja.prz.groups.facade;

import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.List;
import java.util.UUID;

public interface GroupFacade {

	GroupDto createGroup(GroupDto groupDto);

	GroupDto updateGroup(GroupDto groupDto);

	void deleteGroup(Long id);

	GroupDto getGroup(Long id);

	List<GroupDto> getAllGroups();

	GroupDto addChildToGroup(Long groupId, UUID childId);

	GroupDto removeChildFromGroup(Long groupId, UUID childId);

}
