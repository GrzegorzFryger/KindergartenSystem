package pl.edu.pja.prz.core.controller.groups;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pja.prz.groups.facade.GroupFacade;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_GROUPS;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
public class GroupsControllerTest {
	private Long id = 1L;
	private MockMvc mvc;

	@Mock
	private GroupFacade groupFacade;
	@InjectMocks
	private GroupsController groupsController;

	@BeforeEach
	public void setUp() {
		this.mvc = MockMvcBuilders.standaloneSetup(groupsController).build();
	}

	@Test
	public void shouldDelegateApiCallTo_getGroupMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_GROUPS + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).getGroup(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_getAllGroupsMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_GROUPS + "groups")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).getAllGroups();
	}

	@Test
	public void shouldDelegateApiCallTo_deleteGroupMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.delete(API_GROUPS + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).deleteGroup(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_createGroupMethod() throws Exception {
		//Given
		String json = convertToJson(new GroupDto());

		//When
		mvc.perform(MockMvcRequestBuilders.post(API_GROUPS)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).createGroup(any(GroupDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_updateGroupMethod() throws Exception {
		//Given
		String json = convertToJson(new GroupDto());

		//When
		mvc.perform(MockMvcRequestBuilders.put(API_GROUPS)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).updateGroup(any(GroupDto.class));
	}

	@Test
	public void shouldDelegateApiCallTo_addChildToGroupMethod() throws Exception {
		//Given
		UUID childId = UUID.randomUUID();

		//When
		mvc.perform(MockMvcRequestBuilders.put(API_GROUPS + "add/" + id + "/" + childId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).addChildToGroup(anyLong(), any(UUID.class));
	}

	@Test
	public void shouldDelegateApiCallTo_removeChildFromGroupMethod() throws Exception {
		//Given
		UUID childId = UUID.randomUUID();

		//When
		mvc.perform(MockMvcRequestBuilders.put(API_GROUPS + "remove/" + id + "/" + childId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).removeChildFromGroup(anyLong(), any(UUID.class));
	}

	@Test
	public void shouldDelegateApiCallTo_findAllChildrenInGroupMethod() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_GROUPS + "list/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).findAllChildrenInGroup(anyLong());
	}

	@Test
	public void shouldDelegateApiCallTo_getAllGroupsForChild() throws Exception {
		//Given

		//When
		mvc.perform(MockMvcRequestBuilders.get(API_GROUPS + "groupList/" + UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//Then
		verify(groupFacade, only()).getAllGroupsForChild(any(UUID.class));
	}
}
