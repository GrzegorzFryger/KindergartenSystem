package pl.edu.pja.prz.core.controller.receivables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.core.controller.receivables.ReceivablesController;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class ReceivablesControllerTest {
    private static final String BASE = "/api/receivables/";
    private MockMvc mvc;

    @Mock
    private ReceivablesFacade receivablesFacade;

    @InjectMocks
    private ReceivablesController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_getAllTransactionsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllUnassignedTransactions();
    }

    @Test
    public void Should_DelegateApiCallTo_getTransactionMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getTransaction(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_deleteTransactionMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(BASE + "transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).deleteTransaction(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_createTransactionMethod() throws Exception {
        //Given
        String json = convertToJson(new Transaction());

        //When
        mvc.perform(MockMvcRequestBuilders.post(BASE + "transactions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).create(any(Transaction.class));
    }

    @Test
    public void Should_DelegateApiCallTo_updateTransactionMethod() throws Exception {
        //Given
        String json = convertToJson(new Transaction());

        //When
        mvc.perform(MockMvcRequestBuilders.put(BASE + "transactions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).update(any(Transaction.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllCashPaymentsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "cash-payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllCashPayments();
    }

    @Test
    public void Should_DelegateApiCallTo_getCashPaymentMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "cash-payments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getCashPayment(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_deleteCashPaymentMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(BASE + "cash-payments/1")
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
        mvc.perform(MockMvcRequestBuilders.post(BASE + "cash-payments")
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
        mvc.perform(MockMvcRequestBuilders.put(BASE + "cash-payments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).update(any(CashPayment.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllIncomingPaymentsForChildMethod() throws Exception {
        //Given
        String childId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "payments/child/" + childId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllIncomingPaymentsByChildId(any(UUID.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllIncomingPaymentsForChildMethod2() throws Exception {
        //Given
        String childId = UUID.randomUUID().toString();
        String from = LocalDate.now().toString();
        String to = LocalDate.now().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(
                BASE + "payments/child/" + childId + "/" + from + "/" + to + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllIncomingPaymentsByChildId(
                any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllIncomingPaymentsForGuardianMethod() throws Exception {
        //Given
        String guardianId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(BASE + "payments/guardian/" + guardianId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllIncomingPaymentsByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllIncomingPaymentsForGuardianMethod2() throws Exception {
        //Given
        String guardianId = UUID.randomUUID().toString();
        String from = LocalDate.now().toString();
        String to = LocalDate.now().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(
                BASE + "payments/guardian/" + guardianId + "/" + from + "/" + to + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllIncomingPaymentsByGuardianId(
                any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }
}
