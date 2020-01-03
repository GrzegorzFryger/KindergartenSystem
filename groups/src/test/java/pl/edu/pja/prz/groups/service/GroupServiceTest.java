package pl.edu.pja.prz.groups.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.model.GroupBuilder;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @InjectMocks
    GroupServiceImpl groupService;
    private Group group;
    @Mock
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        groupService = new GroupServiceImpl(groupRepository);
        group = new GroupBuilder()
                .withGroupName("Group name")
                .withGroupDescription("Group Description")
                .withChildren(new ArrayList<>())
                .build();
    }

    @Test
    void groupServiceNotNull() {
        //Given

        //When

        //Then
        assertNotNull(groupService);
    }

    @Test
    void shouldCreateNewGroup() {
        //Given

        //When
        groupService.createGroup(group);

        //Then
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void shouldReturnAllGroups() {
        //Given
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        when(groupRepository.findAll()).thenReturn(groupList);

        //When
        groupService.getAllGroups();

        //Then
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnGroupWithGivenId() {
        //Given
        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        //When
        groupService.getGroup(1L);

        //Then
        verify(groupRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteGroupWithGivenId() {
        //Given
        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        //When
        groupService.deleteGroup(1L);

        //Then
        verify(groupRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionIfGroupToDeleteDoesntExist() {
        //Given

        //When
        Assertions.assertThrows(NullPointerException.class, () -> {
            groupService.deleteGroup(123L);
        });

        //Then
        verify(groupRepository, times(0)).delete(any(Group.class));
    }

    @Test
    void shouldUpdateGroupById() {
        //Given
        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        //When
        groupService.updateGroup(group, 1L);

        //Then
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void shouldThrowExceptionIfGroupToUpdateDoesntExist() {
        //Given

        //When
        Assertions.assertThrows(NullPointerException.class, () -> {
            groupService.updateGroup(group, 123L);
        });

        //Then
        verify(groupRepository, times(0)).save(any(Group.class));
    }
}