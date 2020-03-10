package pl.edu.pja.prz.core.controller.finances;

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
import pl.edu.pja.prz.finances.facade.FinancesFacade;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_FINANCES;

@ExtendWith(MockitoExtension.class)
class FinancesControllerTest {
    private MockMvc mvc;

    @Mock
    private FinancesFacade financesFacade;

    @InjectMocks
    private FinancesController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_getBalanceMethod() throws Exception {
        //Given
        String childId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_FINANCES + "balance/" + childId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(financesFacade, only()).getBalance(any(UUID.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getBalancesMethod() throws Exception {
        //Given
        String guardianId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_FINANCES + "balance-list/" + guardianId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(financesFacade, only()).getBalances(any(UUID.class));
    }

}
