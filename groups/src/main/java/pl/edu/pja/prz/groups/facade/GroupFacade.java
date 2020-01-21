package pl.edu.pja.prz.groups.facade;

import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.List;

public interface GroupFacade {

	GroupDto createGroup(GroupDto groupDto);

	GroupDto updateGroup(GroupDto groupDto, Long groupToUpdateId);

	void deleteGroup(Long id);

	GroupDto getGroup(Long id);

	List<GroupDto> getAllGroups();

}
