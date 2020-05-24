package pl.edu.pja.prz.core.controller.payments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pja.prz.payments.facade.RecurringPaymentFacade;
import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class PaymentsControllerTest {

    private MockMvc mvc;

    @Mock
    private RecurringPaymentFacade recurringPaymentFacade;

    @InjectMocks
    private PaymentsController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_findAllPaymentsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "recurring-payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).findAllPayments();
    }

    @Test
    public void Should_DelegateApiCallTo_findPaymentByIdMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "recurring-payment/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).findById(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_createTuitionMethod() throws Exception {
        //Given
        String json = convertToJson(new RecurringPaymentDto());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_PAYMENTS + "recurring-payment/tuition")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).createTuition(any(RecurringPaymentDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_createOtherMethod() throws Exception {
        //Given
        String json = convertToJson(new RecurringPaymentDto());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_PAYMENTS + "recurring-payments/other")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).createOtherPayment(any(RecurringPaymentDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_updatePaymentMethod() throws Exception {
        //Given
        String json = convertToJson(new RecurringPaymentDto());

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_PAYMENTS + "recurring-payments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).updatePayment(any(RecurringPaymentDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_deletePaymentByIdMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(API_PAYMENTS + "recurring-payments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).deletePayment(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_markAsCancelPaymentMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_PAYMENTS + "recurring-payments/cancel/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).markAsCancelPayment(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_findAllRecurringPaymentsByChildIdMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "recurring-payments/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(recurringPaymentFacade, only()).findAllByChild(any(UUID.class));
    }
}