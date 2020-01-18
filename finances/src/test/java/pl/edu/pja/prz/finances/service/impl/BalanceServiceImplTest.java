package pl.edu.pja.prz.finances.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.repository.BalanceRepository;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {

    private Balance balance;
    @Mock
    private BalanceRepository repository;
    @Mock
    private BalanceHistoryService historyService;
    private BalanceServiceImpl balanceService;

    @BeforeEach
    public void setUp() {
        balanceService = new BalanceServiceImpl(repository, historyService);

        balance = new Balance();
        balance.setAmount(new BigDecimal("50.00"));
        balance.setChildId(UUID.randomUUID());
        balance.setGuardianId(UUID.randomUUID());
    }

    @Test
    public void Should_GetBalance() {
        //Given

        //When
        when(repository.getByChildId(any(UUID.class))).thenReturn(Optional.ofNullable(balance));
        Balance result = balanceService.getBalance(UUID.randomUUID());

        //Then
        assertNotNull(result);
        verify(repository, times(1)).getByChildId(any(UUID.class));
    }


    @Test
    public void Should_ThrowException_When_BalanceNotFound() {
        //Given

        //When
        when(repository.getByChildId(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            Balance result = balanceService.getBalance(UUID.randomUUID());
        });

        //Then
        verify(repository, times(1)).getByChildId(any(UUID.class));
    }

    @Test
    public void Should_GetAllBalances() {
        //Given
        List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        //When
        when(repository.getAllByGuardianId(any(UUID.class))).thenReturn(balanceList);
        List<Balance> result = balanceService.getBalances(UUID.randomUUID());

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).getAllByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_IncreaseBalance() {
        //Given

        //When
        when(repository.getByChildId(any(UUID.class))).thenReturn(Optional.ofNullable(balance));
        Balance result = balanceService.increaseBalance(UUID.randomUUID(), new BigDecimal("50.00"), "PAYMENT");

        //Then
        assertNotNull(result);
        assertEquals(new BigDecimal("100.00"), result.getAmount());
        verify(repository, times(1)).save(any(Balance.class));
        verify(historyService, times(1))
                .saveBalanceInHistory(any(UUID.class), any(BigDecimal.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_ThrowException_When_AmountToIncreaseIsNegative() {
        //Given

        //When
        Assertions.assertThrows(BusinessException.class, () -> {
            balanceService.increaseBalance(UUID.randomUUID(), new BigDecimal("-50.00"), "PAYMENT");
        });

        //Then
    }

    @Test
    public void Should_DecreaseBalance() {
        //Given

        //When
        when(repository.getByChildId(any(UUID.class))).thenReturn(Optional.ofNullable(balance));
        Balance result = balanceService.decreaseBalance(UUID.randomUUID(), new BigDecimal("-50.00"), "PAYMENT");

        //Then
        assertNotNull(result);
        assertEquals(new BigDecimal("0.00"), result.getAmount());
        verify(repository, times(1)).save(any(Balance.class));
        verify(historyService, times(1))
                .saveBalanceInHistory(any(UUID.class), any(BigDecimal.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_ThrowException_When_AmountToDecreaseIsPositive() {
        //Given

        //When
        Assertions.assertThrows(BusinessException.class, () -> {
            balanceService.decreaseBalance(UUID.randomUUID(), new BigDecimal("50.00"), "PAYMENT");
        });

        //Then
    }


    @Test
    public void Should_SaveBalance() {
        //Given

        //When
        balanceService.saveBalance(balance);

        //Then
        verify(repository, times(1)).save(any(Balance.class));
    }
}