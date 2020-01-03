package pl.edu.pja.prz.receivables.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

@Mapper
public interface CashPaymentMapper {
    CashPaymentMapper INSTANCE = Mappers.getMapper(CashPaymentMapper.class);

    IncomingPaymentDto cashPaymentToDto(CashPayment cashPayment);

    @BeforeMapping
    default void assignProperType(@MappingTarget IncomingPaymentDto dto) {
        dto.setPaymentType(PaymentType.CASH);
    }
}
