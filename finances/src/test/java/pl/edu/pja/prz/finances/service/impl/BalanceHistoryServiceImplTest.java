package pl.edu.pja.prz.finances.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.builder.BalanceHistoryBuilder;
import pl.edu.pja.prz.finances.repository.BalanceHistoryRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceHistoryServiceImplTest {

    private BalanceHistory record;

    @Mock
    private BalanceHistoryRepository repository;

    private BalanceHistoryServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new BalanceHistoryServiceImpl(repository);

        BalanceHistory record = new BalanceHistoryBuilder()
                .withAmountOfChange( new BigDecimal("-200.50"))
                .withBalanceBeforeChange(new BigDecimal("100.23"))
                .withChildId(UUID.randomUUID())
                .build();
    }

    @Test
    public void Should_GetAllHistoryRecordsForChild() {
        //Given
        List<BalanceHistory> records = new ArrayList<>();
        records.add(record);

        //When
        when(repository.findAllByChildId(any(UUID.class))).thenReturn(records);
        List<BalanceHistory> result = service.getAllHistoryRecordsForChild(UUID.randomUUID());

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAllByChildId(any(UUID.class));
    }

    @Test
    public void Should_SaveBalanceHistoryRecord() {
        //Given

        //When
        service.saveBalanceInHistory(UUID.randomUUID(),
                new BigDecimal("200.10"),
                new BigDecimal("100.20"));

        //Then
        verify(repository, times(1)).save(any(BalanceHistory.class));
    }
}