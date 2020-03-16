package pl.edu.pja.prz.finances.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
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
        facade = new FinancesFacadeImpl(balanceService);

        balance = new Balance();
        balance.setBalance(new BigDecimal("50.00"));
        balance.setLiabilities(new BigDecimal("50.00"));
        balance.setReceivables(new BigDecimal("100.00"));
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
    public void Should_IncreaseBalance() {
        //Given

        //When
        facade.increaseBalance(UUID.randomUUID(), new BigDecimal("50.00"), "Some title");

        //Then
        verify(balanceService, times(1))
                .increaseBalance(any(UUID.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_DecreaseBalance() {
        //Given

        //When
        facade.decreaseBalance(UUID.randomUUID(), new BigDecimal("-50.00"), "Some title");

        //Then
        verify(balanceService, times(1))
                .decreaseBalance(any(UUID.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_ApplyBalanceCorrection() {
        //Given

        //When
        facade.applyBalanceCorrection(UUID.randomUUID(), new BigDecimal("-50.00"), "Some title");

        //Then
        verify(balanceService, times(1))
                .applyBalanceCorrection(any(UUID.class), any(BigDecimal.class), anyString());
    }
}
