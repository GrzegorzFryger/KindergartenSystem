package pl.edu.pja.prz.account.mapper;

import org.mapstruct.Mapper;
import pl.edu.pja.prz.account.facade.dto.RoleDto;
import pl.edu.pja.prz.account.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	Role toRole(RoleDto roleDto);
	RoleDto fromRole(Role role);
}
