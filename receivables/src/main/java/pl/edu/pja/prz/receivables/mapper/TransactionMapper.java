package pl.edu.pja.prz.receivables.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    IncomingPaymentDto transactionToDto(Transaction transaction);

    @BeforeMapping
    default void assignProperType(@MappingTarget IncomingPaymentDto dto) {
        dto.setPaymentType(PaymentType.TRANSFER);
    }
}
