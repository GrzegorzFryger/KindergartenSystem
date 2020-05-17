package pl.edu.pja.prz.groups.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;
import pl.edu.pja.prz.groups.facade.mapper.GroupMapper;
import pl.edu.pja.prz.groups.facade.mapper.GroupMapperImpl;
import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupFacadeTest {
	private final Long testId = 1L;

	@Mock
	private GroupService groupService;
	@Mock
	private ChildFacade childFacade;

	private GroupMapper groupMapper;


	private Group group;
	private GroupDto dto;
	private GroupFacade facade;

	private ChildDto childDto;

	@BeforeEach
	public void setUp() {
		groupMapper = new GroupMapperImpl();
		facade = new GroupFacadeImpl(groupService, groupMapper, childFacade);
		group = new Group();
		dto = new GroupDto();
		childDto = new ChildDto();
	}

	@Test
	public void shouldGetGroup() {
		//Given

		//When
		when(groupService.getGroup(anyLong())).thenReturn(group);
		GroupDto result = facade.getGroup(testId);

		//Then
		verify(groupService, times(1)).getGroup(testId);
		assertNotNull(result);
	}

	@Test
	public void shouldGetAllGroups() {
		//Given
		List<Group> groupList = new ArrayList<>();
		groupList.add(group);

		//When
		when(groupService.getAllGroups()).thenReturn(groupList);
		List<GroupDto> result = facade.getAllGroups();

		//Then
		assertEquals(1, result.size());
		verify(groupService, only()).getAllGroups();
	}

	@Test
	public void shouldDeleteGroup() {
		//Given

		//When
		facade.deleteGroup(testId);

		//Then
		verify(groupService, times(1)).deleteGroup(testId);
	}

	@Test
	public void shouldCreateGroup() {
		//Given

		//When
		facade.createGroup(dto);

		//Then
		verify(groupService, times(1)).createGroup(any(Group.class));
	}

	@Test
	public void shouldUpdateGroup() {
		//Given

		//When
		facade.updateGroup(dto);

		//Then
		verify(groupService, times(1)).updateGroup(any(Group.class));
	}

	@Test
	public void shouldAddChildToGroup() {
		//Given
		UUID childId = UUID.randomUUID();
		when(childFacade.findChildById(any(UUID.class))).thenReturn(childDto);
		when(groupService.addChildToGroup(anyLong(), any(Child.class))).thenReturn(group);

		//When
		facade.addChildToGroup(1L, childId);

		//Then
		verify(groupService, times(1)).addChildToGroup(anyLong(), any(Child.class));
	}

	@Test
	public void shouldRemoveChildFromGroup() {
		//Given
		UUID childId = UUID.randomUUID();

		//When
		facade.removeChildFromGroup(1L, childId);

		//Then
		verify(groupService, times(1)).removeChildFromGroup(anyLong(), any(UUID.class));
	}

	@Test
	public void shouldFindAllChildrenInGroup() {
		//Given

		//When
		facade.findAllChildrenInGroup(1L);

		//Then
		verify(groupService, times(1)).findIdOfAllChildrenInGroup(anyLong());
	}
}
