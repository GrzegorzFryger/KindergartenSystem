package pl.edu.pja.prz.groups.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @InjectMocks
    GroupServiceImpl groupService;
    @Mock
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        groupService = new GroupServiceImpl(groupRepository);
    }

    @Test
    void groupServiceNotNull() {
        assertNotNull(groupService);
    }

    @Test
    void shouldCreateNewGroup() {
        groupService.createGroup("Group Name", "Group Description");
    }
}