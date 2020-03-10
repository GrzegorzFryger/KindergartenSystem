package pl.edu.pja.prz.groups.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private static final String GROUP = "Group";

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        if (groupRepository.findById(group.getId()).isEmpty()) {
            throw new ElementNotFoundException(GROUP, group.getId());
        }
        Group groupToUpdate = getGroup(group.getId());
        if (groupToUpdate.getGroupName() != null) {
            groupToUpdate.setGroupName(group.getGroupName());
        }
        if (groupToUpdate.getGroupDescription() != null) {
            groupToUpdate.setGroupDescription(group.getGroupDescription());
        }
        //TODO: decide if this method should add children to the list, or if I should implement another method for it

        return groupRepository.save(groupToUpdate);
    }

    @Override
    public void deleteGroup(Long id) {
        if (groupRepository.findById(id).isEmpty()) {
            throw new ElementNotFoundException(GROUP, id);
        }
        groupRepository.deleteById(id);

    }

    @Override
    public Group getGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException(GROUP, id));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}