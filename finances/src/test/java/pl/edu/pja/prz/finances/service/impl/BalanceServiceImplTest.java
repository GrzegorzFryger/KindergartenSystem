package pl.edu.pja.prz.finances.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.builder.BalanceHistoryBuilder;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {

    private List<BalanceHistory> balanceHistories;

    @Mock
    private BalanceHistoryService historyService;
    private BalanceServiceImpl balanceService;

    @BeforeEach
    public void setUp() {
        balanceService = new BalanceServiceImpl(historyService);

        balanceHistories = new ArrayList<>();

        BalanceHistory balanceHistory = new BalanceHistoryBuilder()
                .withAmountOfChange(new BigDecimal("50.00"))
                .withChildId(UUID.randomUUID())
                .withTitle("PAYMENT")
                .build();

        balanceHistories.add(balanceHistory);
    }

    @Test
    public void Should_GetBalance() {
        //Given

        //When
        when(historyService.getAllHistoryRecordsForChild(any(UUID.class))).thenReturn(balanceHistories);
        Balance result = balanceService.getBalance(UUID.randomUUID());

        //Then
        assertNotNull(result);
        verify(historyService, times(1)).getAllHistoryRecordsForChild(any(UUID.class));
        assertEquals(new BigDecimal("50.00"), result.getBalance());
    }


    @Test
    public void Should_ThrowException_When_BalanceNotFound() {
        //Given

        //When
        when(historyService.getAllHistoryRecordsForChild(any(UUID.class))).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            Balance result = balanceService.getBalance(UUID.randomUUID());
        });

        //Then
        verify(historyService, times(1)).getAllHistoryRecordsForChild(any(UUID.class));
    }

    @Test
    public void Should_IncreaseBalance() {
        //Given

        //When
        balanceService.increaseBalance(UUID.randomUUID(), new BigDecimal("50.00"), "PAYMENT");

        //Then
        verify(historyService, times(1))
                .saveBalanceInHistory(any(UUID.class), any(BigDecimal.class), anyString());
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
        balanceService.decreaseBalance(UUID.randomUUID(), new BigDecimal("-50.00"), "PAYMENT");

        //Then
        verify(historyService, times(1))
                .saveBalanceInHistory(any(UUID.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_DecreaseBalance_When_AmountIsPositive() {
        //Given

        //When
        balanceService.decreaseBalance(UUID.randomUUID(), new BigDecimal("50.00"), "PAYMENT");

        //Then
        verify(historyService, times(1))
                .saveBalanceInHistory(any(UUID.class), any(BigDecimal.class), anyString());
    }

    @Test
    public void Should_CalculateBalance_When_ThereAreOnlyReceivables() {
        //Given
        List<BalanceHistory> balanceHistories = Arrays.asList(
                buildBalanceHistory("50.00"),
                buildBalanceHistory("20.00")
        );

        //When
        Balance result = balanceService.calculateBalance(balanceHistories);

        assertNotNull(result);
        assertEquals(new BigDecimal("70.00"), result.getBalance());
        assertEquals(new BigDecimal("70.00"), result.getReceivables());
        assertEquals(BigDecimal.ZERO, result.getLiabilities());
    }

    @Test
    public void Should_CalculateBalance_When_ThereAreOnlyLiabilities() {
        //Given
        List<BalanceHistory> balanceHistories = Arrays.asList(
                buildBalanceHistory("-50.00"),
                buildBalanceHistory("-10.00")
        );

        //When
        Balance result = balanceService.calculateBalance(balanceHistories);

        assertNotNull(result);
        assertEquals(new BigDecimal("-60.00"), result.getBalance());
        assertEquals(BigDecimal.ZERO, result.getReceivables());
        assertEquals(new BigDecimal("-60.00"), result.getLiabilities());
    }

    @Test
    public void Should_CalculateBalance_When_ThereAreBothReceivablesAndLiabilities() {
        //Given
        List<BalanceHistory> balanceHistories = Arrays.asList(
                buildBalanceHistory("50.00"),
                buildBalanceHistory("20.00"),
                buildBalanceHistory("-50.00"),
                buildBalanceHistory("-10.00")
        );

        //When
        Balance result = balanceService.calculateBalance(balanceHistories);

        assertNotNull(result);
        assertEquals(new BigDecimal("10.00"), result.getBalance());
        assertEquals(new BigDecimal("70.00"), result.getReceivables());
        assertEquals(new BigDecimal("-60.00"), result.getLiabilities());
    }

    private BalanceHistory buildBalanceHistory(String amount) {
        BalanceHistory balanceHistory = new BalanceHistory();
        balanceHistory.setAmountOfChange(new BigDecimal(amount));
        return balanceHistory;
    }
}
