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
import pl.edu.pja.prz.payments.facade.PaymentHistoryFacade;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class PaymentHistoryControllerTest {

    private MockMvc mvc;

    @Mock
    private PaymentHistoryFacade facade;

    @InjectMocks
    private PaymentHistoryController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_findAllPaymentsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "payment-history/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).getAllHistoryOfChild(any(UUID.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllPaymentsHistoryFromMonthMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "payments-history/month")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).getAllPaymentsHistoryFromMonth();
    }

    @Test
    public void Should_DelegateApiCallTo_createTuitionMethod() throws Exception {
        //Given
        String json = convertToJson(new PaymentHistoryDto());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_PAYMENTS + "payment-history/balance-correction")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).applyBalanceCorrectionForPayment(any(PaymentHistoryDto.class));
    }

}