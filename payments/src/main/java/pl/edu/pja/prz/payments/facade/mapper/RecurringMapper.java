package pl.edu.pja.prz.payments.facade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pja.prz.payments.facade.dto.RecurringPaymentDto;
import pl.edu.pja.prz.payments.model.RecurringPayment;

@Mapper(componentModel = "spring")
public interface RecurringMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "amount", target = "amount")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "child", target = "child.childId")
	@Mapping(source = "guardian", target = "child.guardianId")
	@Mapping(source = "startDate", target = "periodValidity.startDate")
	@Mapping(source = "endDate", target = "periodValidity.endDate")
	@Mapping(source = "type", target = "typeRecurringPayment")
	RecurringPayment toRecurringPayment(RecurringPaymentDto recurringPaymentDto);

	@InheritInverseConfiguration
	RecurringPaymentDto fromRecurringPayment(RecurringPayment recurringPayment);

}
