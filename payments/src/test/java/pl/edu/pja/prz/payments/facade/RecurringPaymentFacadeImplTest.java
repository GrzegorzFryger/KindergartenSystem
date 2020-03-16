package pl.edu.pja.prz.payments.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.payments.facade.dto.RecurringPaymentDto;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapper;
import pl.edu.pja.prz.payments.facade.mapper.RecurringMapperImpl;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.service.RecurringPaymentService;

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

    @BeforeEach
    public void setUp() {
        recurringMapper = new RecurringMapperImpl();

        recurringPaymentFacade = new RecurringPaymentFacadeImpl(recurringPaymentService, recurringMapper);
    }

    @Test
    public void Should_CreateTuition() {
        //Given
        RecurringPaymentDto dto = new RecurringPaymentDto();

        //When
        recurringPaymentFacade.createTuition(dto);

        //Then
        verify(recurringPaymentService, only()).createTuition(any(RecurringPayment.class));
    }

    @Test
    public void Should_CreateOtherPayment() {
        //Given
        RecurringPaymentDto dto = new RecurringPaymentDto();

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
        RecurringPaymentDto dto = new RecurringPaymentDto();

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
