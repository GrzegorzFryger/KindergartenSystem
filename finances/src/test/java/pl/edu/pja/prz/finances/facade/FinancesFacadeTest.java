package pl.edu.pja.prz.finances.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinancesFacadeTest {

    private Balance balance;
    @Mock
    private BalanceService balanceService;
    private FinancesFacade facade;

    @BeforeEach
    public void setUp() {
        facade = new FinancesFacade(balanceService);

        balance = new Balance();
        balance.setAmount(new BigDecimal("50.00"));
        balance.setChildId(UUID.randomUUID());
        balance.setGuardianId(UUID.randomUUID());
    }

    @Test
    public void Should_GetBalance() {
        //Given

        //When
        when(balanceService.getBalance(any(UUID.class))).thenReturn(balance);
        Balance result = facade.getBalance(UUID.randomUUID());

        //Then
        assertNotNull(result);
        verify(balanceService, times(1)).getBalance(any(UUID.class));
    }

    @Test
    public void Should_GetBalanceList() {
        //Given
        List<Balance> balanceList = new ArrayList();
        balanceList.add(balance);

        //When
        when(balanceService.getBalances(any(UUID.class))).thenReturn(balanceList);
        List<Balance> result = facade.getBalances(UUID.randomUUID());

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(balanceService, times(1)).getBalances(any(UUID.class));
    }

    @Test
    public void Should_IncreaseBalance() {
        //Given

        //When
        facade.increaseBalance(UUID.randomUUID(), new BigDecimal("50.00"),"PAYMENT");

        //Then
        verify(balanceService, times(1))
                .increaseBalance(any(UUID.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_DecreaseBalance() {
        //Given

        //When
        facade.decreaseBalance(UUID.randomUUID(), new BigDecimal("-50.00"),"PAYMENT");

        //Then
        verify(balanceService, times(1))
                .decreaseBalance(any(UUID.class), any(BigDecimal.class), anyString());
    }
}