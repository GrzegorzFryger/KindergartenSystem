package pl.edu.pja.prz.groups.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;
import pl.edu.pja.prz.groups.model.Child;
import pl.edu.pja.prz.groups.model.Group;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupMapper {
	Group toGroup(GroupDto groupDto);

	@InheritInverseConfiguration
	GroupDto fromGroup(Group group);

	List<GroupDto> groupListToDtoList(List<Group> groupList);

	@Mapping(source = "id", target = "id")
	Child toChild(ChildDto childDto);
}
