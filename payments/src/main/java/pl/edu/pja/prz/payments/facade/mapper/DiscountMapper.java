package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.dto.DiscountDto;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "typeDiscount", target = "typeDiscount")
    @Mapping(source = "name", target = "name")
    Discount toDiscount(DiscountDto discountDto);

    @InheritInverseConfiguration
    DiscountDto fromDiscount(Discount discount);
}
