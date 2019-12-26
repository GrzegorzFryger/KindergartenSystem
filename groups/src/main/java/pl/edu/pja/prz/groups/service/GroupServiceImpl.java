package pl.edu.pja.prz.groups.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.model.GroupBuilder;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group createGroup(String groupName, String groupDescription) {
        var group = new GroupBuilder()
                .withGroupName(groupName)
                .withGroupDescription(groupDescription)
                .build();
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return null;
    }

    @Override
    public void deleteGroup(Long id) {

    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }
}