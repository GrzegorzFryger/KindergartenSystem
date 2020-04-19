package pl.edu.pja.prz.finances.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.AccountNumberService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinancesFacadeTest {

    @Captor
    ArgumentCaptor<List<UUID>> argumentCaptor;

    private Balance balance;
    @Mock
    private BalanceService balanceService;
    @Mock
    private GuardianFacade guardianFacade;
    @Mock
    private AccountNumberService accountNumberService;

    private FinancesFacade facade;

    @BeforeEach
    public void setUp() {
        facade = new FinancesFacadeImpl(balanceService, guardianFacade, accountNumberService);

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
    public void Should_GetListOfBalancesForAllChildren() {
        //Given
        Set<ChildDto> childDtos = new HashSet<>();
        ChildDto first = new ChildDto();
        ChildDto second = new ChildDto();
        first.setId(UUID.randomUUID());
        second.setId(UUID.randomUUID());
        childDtos.add(first);
        childDtos.add(second);

        //When
        when(guardianFacade.findAllGuardianChildren(any(UUID.class))).thenReturn(childDtos);
        when(balanceService.getBalance(any(UUID.class))).thenReturn(balance);
        List<Balance> result = facade.getBalancesForAllChildren(UUID.randomUUID());

        //Then
        assertNotNull(result);
        verify(balanceService, times(2)).getBalance(any(UUID.class));
        assertEquals(2, result.size());
    }

    @Test
    public void Should_GetBalanceForAllChildren() {
        //Given
        Set<ChildDto> childDtos = new HashSet<>();
        ChildDto first = new ChildDto();
        ChildDto second = new ChildDto();
        first.setId(UUID.randomUUID());
        second.setId(UUID.randomUUID());
        childDtos.add(first);
        childDtos.add(second);

        //When
        when(guardianFacade.findAllGuardianChildren(any(UUID.class))).thenReturn(childDtos);
        when(balanceService.getBalanceForAllChildren(anyList(), any(UUID.class))).thenReturn(balance);
        Balance result = facade.getBalanceForAllChildren(UUID.randomUUID());

        //Then
        assertNotNull(result);
        verify(balanceService, times(1)).getBalanceForAllChildren(argumentCaptor.capture(), any(UUID.class));
        assertEquals(2, argumentCaptor.getValue().size());
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

    @Test
    public void Should_ReturnAccountNumber() {
        //Given

        //When
        facade.getAccountNumber(UUID.randomUUID());

        //Then
        verify(accountNumberService, only()).getAccountNumber(any(UUID.class));
    }
}
