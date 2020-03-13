package pl.edu.pja.prz.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.model.dto.AccountCredentialDto;
import pl.edu.pja.prz.account.model.Account;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface AccountCredentialMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "accountStatus", target = "status")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "roles", target =  "roles")
	@Mapping(source = "password.password", target =  "password")
	AccountCredentialDto fromAccount(Account account);
}
