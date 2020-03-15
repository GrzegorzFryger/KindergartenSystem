package pl.edu.pja.prz.groups.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.model.GroupBuilder;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
	private Group group;
	@Mock
	private GroupRepository repository;
	private GroupServiceImpl service;
	private Child child;

	@BeforeEach
	void setUp() {
		service = new GroupServiceImpl(repository);
		group = new GroupBuilder()
				.withGroupName("Group name")
				.withGroupDescription("Group Description")
				.build();
		group.setId(1L);

		child = new Child();
	}

	@Test
	void shouldCreateNewGroup() {
		//Given

		//When
		service.createGroup(group);

		//Then
		verify(repository, times(1)).save(any(Group.class));
	}

	@Test
	void shouldReturnAllGroups() {
		//Given
		List<Group> groupList = new ArrayList<>();
		groupList.add(group);
		when(repository.findAll()).thenReturn(groupList);

		//When
		service.getAllGroups();

		//Then
		verify(repository, times(1)).findAll();
	}

	@Test
	void shouldReturnGroupWithGivenId() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(group));

		//When
		service.getGroup(1L);

		//Then
		verify(repository, times(1)).findById(1L);
	}

	@Test
	void shouldDeleteGroupWithGivenId() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(group));

		//When
		service.deleteGroup(1L);

		//Then
		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	void shouldThrowExceptionIfGroupToDeleteDoesntExist() {
		//Given

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.deleteGroup(123L);
		});

		//Then
		verify(repository, times(0)).delete(any(Group.class));
	}

	@Test
	void shouldUpdateGroup() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(group));

		//When
		service.updateGroup(group);

		//Then
		verify(repository, times(1)).save(any(Group.class));
	}

	@Test
	void shouldThrowExceptionIfGroupToUpdateDoesntExist() {
		//Given

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.updateGroup(group);
		});

		//Then
		verify(repository, times(0)).save(any(Group.class));
	}

	@Test
	void shouldAddChildToGroup() {
		//Given
		when(repository.findById(anyLong())).thenReturn(Optional.of(group));

		//When
		service.addChildToGroup(1L, child);

		//Then
		verify(repository, times(1)).save(any(Group.class));
	}

	@Test
	void shouldThrowExceptionIfGroupNotFound() {
		//Given

		//When
		Assertions.assertThrows(ElementNotFoundException.class, () -> {
			service.addChildToGroup(1L, child);
		});

		//Then
		verify(repository, times(0)).save(any(Group.class));
	}
}