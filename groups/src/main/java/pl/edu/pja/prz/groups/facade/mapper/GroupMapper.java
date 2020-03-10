package pl.edu.pja.prz.groups.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;
import pl.edu.pja.prz.groups.model.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
	Group toGroup(GroupDto groupDto);

	@InheritInverseConfiguration
	GroupDto fromGroup(Group group);
}
