package pl.edu.pja.prz.account.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface AccountMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "fullName.name")
	@Mapping(source = "surname", target = "fullName.surname")
	@Mapping(source = "postalCode", target = "address.postalCode")
	@Mapping(source = "city", target = "address.city")
	@Mapping(source = "streetNumber", target = "address.streetNumber")
	@Mapping(source = "phone", target = "phoneNumber.phone")
	@Mapping(source = "status", target = "accountStatus")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "roles", target =  "roles")
	@Mapping(source = "password", target =  "password.password")
	Account toAccount(AccountDto accountDto);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "fullName.name")
	@Mapping(source = "surname", target = "fullName.surname")
	@Mapping(source = "postalCode", target = "address.postalCode")
	@Mapping(source = "city", target = "address.city")
	@Mapping(source = "streetNumber", target = "address.streetNumber")
	@Mapping(source = "phone", target = "phoneNumber.phone")
	Person toPerson(AccountDto accountDto);

	@InheritInverseConfiguration(name = "toAccount")
	AccountDto fromAccount(Account account);
}
