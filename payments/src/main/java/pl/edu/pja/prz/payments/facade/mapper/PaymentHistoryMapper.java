package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.payments.model.PaymentHistory;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "childId", target = "childId")
    @Mapping(source = "guardianId", target = "guardianId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "typeRecurringPayment", target = "typeRecurringPayment")
    @Mapping(source = "operationType", target = "operationType")
    PaymentHistory toPaymentHistory(PaymentHistoryDto paymentHistoryDto);

    @InheritInverseConfiguration
    PaymentHistoryDto fromPaymentHistory(PaymentHistory paymentHistory);
}
