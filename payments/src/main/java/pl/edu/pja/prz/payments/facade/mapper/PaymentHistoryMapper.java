package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.edu.pja.prz.payments.model.PaymentHistory;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {

    PaymentHistory toPaymentHistory(PaymentHistoryDto paymentHistoryDto);

    @InheritInverseConfiguration
    PaymentHistoryDto fromPaymentHistory(PaymentHistory paymentHistory);
}
