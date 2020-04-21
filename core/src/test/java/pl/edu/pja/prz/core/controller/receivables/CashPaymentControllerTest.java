package pl.edu.pja.prz.core.controller.receivables;

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
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class CashPaymentControllerTest {
    private MockMvc mvc;

    @Mock
    private ReceivablesFacade receivablesFacade;

    @InjectMocks
    private CashPaymentController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_getAllCashPaymentsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "cash-payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllCashPayments();
    }

    @Test
    public void Should_DelegateApiCallTo_getCashPaymentMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "cash-payments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getCashPayment(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_deleteCashPaymentMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(API_RECEIVABLES + "cash-payments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).deleteCashPayment(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_createCashPaymentMethod() throws Exception {
        //Given
        String json = convertToJson(new CashPayment());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_RECEIVABLES + "cash-payments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).create(any(CashPayment.class));
    }

    @Test
    public void Should_DelegateApiCallTo_updateCashPaymentMethod() throws Exception {
        //Given
        String json = convertToJson(new CashPayment());

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_RECEIVABLES + "cash-payments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).update(any(CashPayment.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllCashPaymentsForChild() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "cash-payments/child/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllCashPaymentsForChild(any(UUID.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllCashPaymentsForLastMonth() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "cash-payments/past-month")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllCashPaymentsForPastMonth(any(LocalDate.class), any(LocalDate.class));
    }

}
