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
import pl.edu.pja.prz.receivables.model.Transaction;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class TransactionsControllerTest {
    private MockMvc mvc;

    @Mock
    private ReceivablesFacade receivablesFacade;

    @InjectMocks
    private TransactionsController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_getAllTransactionsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllUnassignedTransactions();
    }

    @Test
    public void Should_DelegateApiCallTo_getTransactionMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getTransaction(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_deleteTransactionMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(API_RECEIVABLES + "transactions/1")
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
        mvc.perform(MockMvcRequestBuilders.post(API_RECEIVABLES + "transactions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).create(any(Transaction.class));
    }

    @Test
    public void Should_DelegateApiCallTo_assignTransactionToChild() throws Exception {
        //Given
        String json = convertToJson(new Transaction());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_RECEIVABLES + "transactions/assign/"
                + UUID.randomUUID() + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).update(any(Transaction.class));
    }

    @Test
    public void Should_DelegateApiCallTo_updateTransactionMethod() throws Exception {
        //Given
        String json = convertToJson(new Transaction());

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_RECEIVABLES + "transactions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).update(any(Transaction.class));
    }
}
