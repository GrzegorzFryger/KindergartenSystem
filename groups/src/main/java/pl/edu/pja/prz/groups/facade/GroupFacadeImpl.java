package pl.edu.pja.prz.groups.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;
import pl.edu.pja.prz.groups.facade.mapper.GroupMapper;
import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.service.GroupService;

import java.util.List;
import java.util.UUID;

@Service
public class GroupFacadeImpl implements GroupFacade {
	private final GroupService groupService;
	private final GroupMapper groupMapper;
	private final ChildFacade childFacade;

	@Autowired
	public GroupFacadeImpl(GroupService groupService, GroupMapper groupMapper, ChildFacade childFacade) {
		this.groupService = groupService;
		this.groupMapper = groupMapper;
		this.childFacade = childFacade;
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
	public GroupDto updateGroup(GroupDto groupDto) {
		return groupMapper.fromGroup(
				groupService.updateGroup(
						groupMapper.toGroup(groupDto)
				)
		);
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
		return groupMapper.groupListToDtoList(
				groupService.getAllGroups()
		);
	}

	@Override
	public GroupDto addChildToGroup(Long groupId, UUID childId) {
		ChildDto childFromDb = childFacade.findChildById(childId);
		Child childToAdd = groupMapper.toChild(childFromDb);
		return groupMapper.fromGroup(
				groupService.addChildToGroup(groupId, childToAdd)
		);

	}

	@Override
	public GroupDto removeChildFromGroup(Long groupId, UUID childId) {
		return null;
	}
}
