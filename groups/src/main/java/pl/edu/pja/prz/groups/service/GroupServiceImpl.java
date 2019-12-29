package pl.edu.pja.prz.groups.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.groups.model.Group;
import pl.edu.pja.prz.groups.repository.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
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
    public Group updateGroup(Group group, Long groupToUpdateId) {
        if (groupRepository.findById(groupToUpdateId).isEmpty()) {
            throw new NullPointerException("Group with id: " + groupToUpdateId + " not found.");
        }
        Group groupToUpdate = getGroup(groupToUpdateId);
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
            throw new NullPointerException("Group with id: " + id + " not found.");
        }
        groupRepository.deleteById(id);

    }

    @Override
    public Group getGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Group with id: " + id + " not found."));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}