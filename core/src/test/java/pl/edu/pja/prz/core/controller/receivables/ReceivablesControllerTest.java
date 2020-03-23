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

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@ExtendWith(MockitoExtension.class)
class ReceivablesControllerTest {
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
    public void Should_DelegateApiCallTo_getAllIncomingPaymentsForChildMethod() throws Exception {
        //Given
        String childId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "payments/child/" + childId)
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
                API_RECEIVABLES + "payments/child/" + childId + "/" + from + "/" + to + "/")
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
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "payments/guardian/" + guardianId)
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
                API_RECEIVABLES + "payments/guardian/" + guardianId + "/" + from + "/" + to + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllIncomingPaymentsByGuardianId(
                any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_DelegateApiCallTo_getAllPaymentMappingsForGuardianMethod() throws Exception {
        //Given
        String guardianId = UUID.randomUUID().toString();

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "payments/mappings/" + guardianId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(receivablesFacade, only()).getAllMappingsForGuardian(any(UUID.class));
    }
}
