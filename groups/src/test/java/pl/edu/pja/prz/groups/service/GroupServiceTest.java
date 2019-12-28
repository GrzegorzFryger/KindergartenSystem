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

import static org.junit.jupiter.api.Assertions.*;

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
}