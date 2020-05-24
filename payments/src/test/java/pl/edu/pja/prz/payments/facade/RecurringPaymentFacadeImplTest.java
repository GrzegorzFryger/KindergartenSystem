package pl.edu.pja.prz.payments.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapper;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapperImpl;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.service.RecurringPaymentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecurringPaymentFacadeImplTest {

    @Mock
    private RecurringPaymentService recurringPaymentService;
    private RecurringMapper recurringMapper;

    private RecurringPaymentFacade recurringPaymentFacade;
    private RecurringPaymentDto dto;

    @BeforeEach
    public void setUp() {
        recurringMapper = new RecurringMapperImpl();

        recurringPaymentFacade = new RecurringPaymentFacadeImpl(recurringPaymentService, recurringMapper);

        dto = new RecurringPaymentDto();
        dto.setAmount(new BigDecimal(1));
        dto.setChild(UUID.randomUUID());
        dto.setDescription("Some description");
        dto.setEndDate(LocalDate.now());
        dto.setGuardian(UUID.randomUUID());
        dto.setId(1L);
        dto.setStartDate(LocalDate.now());
        dto.setType(TypeRecurringPayment.OTHER);
    }

    @Test
    public void Should_CreateTuition() {
        //Given

        //When
        recurringPaymentFacade.createTuition(dto);

        //Then
        verify(recurringPaymentService, only()).createTuition(any(RecurringPayment.class));
    }

    @Test
    public void Should_CreateOtherPayment() {
        //Given

        //When
        recurringPaymentFacade.createOtherPayment(dto);

        //Then
        verify(recurringPaymentService, only()).createOtherPayment(any(RecurringPayment.class));
    }

    @Test
    public void Should_FindRecurringPaymentDtoById() {
        //Given

        //When
        recurringPaymentFacade.findById(1L);

        //Then
        verify(recurringPaymentService, only()).getPaymentById(anyLong());
    }

    @Test
    public void Should_FindAllPayments() {
        //Given

        //When
        recurringPaymentFacade.findAllPayments();

        //Then
        verify(recurringPaymentService, only()).getAllPayments();
    }

    @Test
    public void Should_UpdatePayment() {
        //Given

        //When
        recurringPaymentFacade.updatePayment(dto);

        //Then
        verify(recurringPaymentService, only()).updatePayment(any(RecurringPayment.class));
    }

    @Test
    public void Should_MarkAsCancelPayment() {
        //Given

        //When
        recurringPaymentFacade.markAsCancelPayment(1L);

        //Then
        verify(recurringPaymentService, only()).markAsCancelPayment(anyLong());
    }

    @Test
    public void Should_DeletePayment() {
        //Given

        //When
        recurringPaymentFacade.deletePayment(1L);

        //Then
        verify(recurringPaymentService, only()).deletePayment(anyLong());
    }
}
