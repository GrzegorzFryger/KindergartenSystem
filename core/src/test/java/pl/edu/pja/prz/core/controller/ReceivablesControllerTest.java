package pl.edu.pja.prz.core.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import pl.edu.pja.prz.core.util.LocalDateAdapter;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.builder.TransactionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReceivablesControllerTest {
    private static final String BASE = "/api/receivables/";

    private Long TEST_ID = 1L;
    private Transaction transaction;
    private MockMvc mvc;

    @Mock
    private ReceivablesFacade receivablesFacade;

    @InjectMocks
    private ReceivablesController controller;

    @BeforeEach
    public void setUp() {
        transaction = new TransactionBuilder()
                .withTransactionDate(LocalDate.of(2020, 10, 10))
                .withBookingDate(LocalDate.of(2020, 10, 19))
                .withContractorDetails("Rodzic #001")
                .withTitle("Czesne")
                .withAccountNumber("12345678901234")
                .withBankName("Bank ING")
                .withDetails("WPŁYW")
                .withTransactionNumber("XYZ-1234")
                .withTransactionAmount(new BigDecimal("70.50"))
                .withTransactionCurrency("PLN")
                .build();

        transaction.setId(TEST_ID);

        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_ReadTransaction() throws Exception {
        //Given

        //When
        when(receivablesFacade.getTransaction(TEST_ID)).thenReturn(transaction);

        mvc.perform(MockMvcRequestBuilders.get(BASE + "transactions/" + TEST_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountNumber").value("12345678901234"))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, times(1)).getTransaction(TEST_ID);
    }

    @Test
    public void Should_ReadAllTransactions() throws Exception {
        //Given
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(receivablesFacade.getAllTransactions()).thenReturn(transactionList);

        mvc.perform(MockMvcRequestBuilders.get(BASE + "transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountNumber").value("12345678901234"))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, times(1)).getAllTransactions();
    }

    @Test
    public void Should_DeleteTransaction() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(BASE + "transactions/" + TEST_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, times(1)).delete(TEST_ID);
    }

    @Test
    public void Should_CreateTransaction() throws Exception {
        //Given
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(transaction);

        //When
        mvc.perform(MockMvcRequestBuilders.post(BASE + "transactions")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, times(1)).create(any(Transaction.class));
    }

    @Test
    public void Should_UpdateTransaction() throws Exception {
        //Given
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(transaction);

        //When
        mvc.perform(MockMvcRequestBuilders.put(BASE + "transactions")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, times(1)).update(any(Transaction.class));
    }
}