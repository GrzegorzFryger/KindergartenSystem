package pl.edu.pja.prz.account.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.model.Employee;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface EmployeeMapper {
    @Mapping(source = "fullName.name", target = "name")
    @Mapping(source = "fullName.surname", target = "surname")
    @Mapping(source = "address.postalCode", target = "postalCode")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.streetNumber", target = "streetNumber")
    @Mapping(source = "phoneNumber.phone", target = "phone")
    @Mapping(source = "accountStatus", target = "status")
    EmployeeDto fromEmployee(Employee employee);

    @InheritInverseConfiguration
    Employee toEmployee(EmployeeDto employeeDto);

    @Mapping(source = "phone", target = "phoneNumber.phone")
    @Mapping(source = "name", target = "fullName.name")
    @Mapping(source = "surname", target = "fullName.surname")
    @Mapping(source = "postalCode", target = "address.postalCode")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "streetNumber", target = "address.streetNumber")
    Person toPerson(AccountDto accountDto);
}
