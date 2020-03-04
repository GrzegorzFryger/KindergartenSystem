package pl.edu.pja.prz.account.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.model.Borough;

@Mapper(componentModel = "spring")
public interface BoroughMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "postalCode", target = "address.postalCode")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "streetNumber", target = "address.streetNumber")
    @Mapping(source = "phone", target = "phone.phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nipNumber", target = "nipNumber")
    Borough toBorough(BoroughDto boroughDto);

    @InheritInverseConfiguration
    BoroughDto fromBorough(Borough borough);
}