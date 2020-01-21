package pl.edu.pja.prz.groups.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;
import pl.edu.pja.prz.groups.facade.mapper.GroupMapper;
import pl.edu.pja.prz.groups.service.GroupService;

import java.util.List;

@Service
public class GroupFacadeImpl implements GroupFacade {
	private final GroupService groupService;
	private final GroupMapper groupMapper;

	@Autowired
	public GroupFacadeImpl(GroupService groupService, GroupMapper groupMapper) {
		this.groupService = groupService;
		this.groupMapper = groupMapper;
	}

	@Override
	public GroupDto createGroup(GroupDto groupDto) {
		return groupMapper.fromGroup(
				groupService.createGroup(
						groupMapper.toGroup(groupDto)
				)
		);
	}

	@Override
	public GroupDto updateGroup(GroupDto groupDto, Long groupToUpdateId) {
		return null;
	}

	@Override
	public void deleteGroup(Long id) {
		groupService.deleteGroup(id);
	}

	@Override
	public GroupDto getGroup(Long id) {
		return groupMapper.fromGroup(
				groupService.getGroup(id)
		);
	}

	@Override
	public List<GroupDto> getAllGroups() {
		//TODO: Figure out if I need another mapper for this
		return null;
	}
}
