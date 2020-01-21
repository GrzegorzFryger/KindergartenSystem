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
		return null;
	}

	@Override
	public GroupDto updateGroup(GroupDto groupDto, Long groupToUpdateId) {
		return null;
	}

	@Override
	public void deleteGroup(Long id) {
	}

	@Override
	public GroupDto getGroup(Long id) {
		return null;
	}

	@Override
	public List<GroupDto> getAllGroups() {
		return null;
	}
}
