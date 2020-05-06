package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.dto.DiscountDto;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    Discount toDiscount(DiscountDto discountDto);

    @InheritInverseConfiguration
    DiscountDto fromDiscount(Discount discount);
}
