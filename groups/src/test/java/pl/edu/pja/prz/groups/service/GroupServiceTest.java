package pl.edu.pja.prz.groups.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    private Group group;
    @InjectMocks
    GroupServiceImpl groupService;
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
        assertNotNull(groupService);
    }

    @Test
    void shouldCreateNewGroup() {
        groupService.createGroup(group);
    }

    @Test
    void shouldReturnAllGroups() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        when(groupRepository.findAll()).thenReturn(groupList);
        groupService.getAllGroups();
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnGroupWithGivenId() {
        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        groupService.getGroup(1L);
        verify(groupRepository,times(1)).findById(1L);
    }
}