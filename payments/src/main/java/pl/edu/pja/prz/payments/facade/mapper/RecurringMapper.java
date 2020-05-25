package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;

@Mapper(componentModel = "spring")
public interface RecurringMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "amount", target = "baseAmount")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "child", target = "childId")
	@Mapping(source = "guardian", target = "guardianId")
	@Mapping(source = "startDate", target = "periodValidity.startDate")
	@Mapping(source = "endDate", target = "periodValidity.endDate")
	@Mapping(source = "type", target = "typeRecurringPayment")
	RecurringPayment toRecurringPayment(RecurringPaymentDto recurringPaymentDto);

	@InheritInverseConfiguration
	RecurringPaymentDto fromRecurringPayment(RecurringPayment recurringPayment);

}
