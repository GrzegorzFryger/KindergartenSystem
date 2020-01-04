package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.builder.TransactionBuilder;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;
import pl.edu.pja.prz.receivables.service.impl.TransactionServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    private Transaction transaction;

    @Mock
    private TransactionRepository repository;

    private TransactionServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new TransactionServiceImpl(repository);

        transaction = new TransactionBuilder()
                .withTransactionDate(LocalDate.of(2020, 10, 10))
                .withBookingDate(LocalDate.of(2020, 10, 19))
                .withContractorDetails("Rodzic #001")
                .withTitle("Czesne")
                .withAccountNumber("1234567890")
                .withBankName("TEST BANK")
                .withDetails("WPŁYW")
                .withTransactionNumber("1234")
                .withTransactionAmount(new BigDecimal("55.55"))
                .withTransactionCurrency("PLN")
                .build();

        transaction.setId(1L);
    }

    @Test
    public void Should_SaveTransaction() {
        //Given

        //When
        service.save(transaction);

        //Then
        verify(repository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void Should_Not_SaveTransaction_When_AmountIsNegative() {
        //Given
        transaction.setTransactionAmount(new BigDecimal("-0.01"));

        //When
        service.save(transaction);

        //Then
        verify(repository, never()).save(any(Transaction.class));
    }

    @Test
    public void Should_UpdateTransaction() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(transaction));

        //When
        service.update(transaction);

        //Then
        verify(repository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void Should_ThrowException_When_TransactionToUpdateDoesNotExists() {
        //Given

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.update(transaction);
        });

        //Then
        verify(repository, times(0)).save(any(Transaction.class));
    }

    @Test
    public void Should_DeleteTransaction() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(transaction));

        //When
        service.delete(1L);

        //Then
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    public void Should_ThrowException_When_TransactionToDeleteDoesNotExists() {
        //Given

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.delete(9999L);
        });

        //Then
        verify(repository, times(0)).delete(any(Transaction.class));
    }

    @Test
    public void Should_GetTransaction() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(transaction));

        //When
        service.getTransaction(1L);

        //Then
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void Should_ThrowException_When_TransactionNotFound() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.getTransaction(1L);
        });

        //Then
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    public void Should_GetAllUnassignedTransactions() {
        //Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(repository.findAllByGuardianIdIsNullOrChildIdIsNull()).thenReturn(transactions);

        //When
        List<Transaction> result = service.getAllUnassignedTransactions();

        //Then
        verify(repository, times(1)).findAllByGuardianIdIsNullOrChildIdIsNull();
    }

    @Test
    public void Should_GetAllTransactionsByChildId() {
        //Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(repository.findAllByChildId(any(UUID.class))).thenReturn(transactions);

        //When
        List<Transaction> result = service.getAllTransactionsByChildId(UUID.randomUUID());

        //Then
        verify(repository, times(1)).findAllByChildId(any(UUID.class));
    }

    @Test
    public void Should_GetAllTransactionsByGuardianId() {
        //Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(repository.findAllByGuardianId(any(UUID.class))).thenReturn(transactions);

        //When
        List<Transaction> result = service.getAllTransactionsByGuardianId(UUID.randomUUID());

        //Then
        verify(repository, times(1)).findAllByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_GetAllTransactionsByChildIdBetweenDates() {
        //Given
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(repository.findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);

        //When
        List<Transaction> result = service.getAllTransactionsByChildId(UUID.randomUUID(), start, end);

        //Then
        verify(repository, times(1))
                .findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_GetAllTransactionsByGuardianIdBetweenDates() {
        //Given
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(repository.findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);

        //When
        List<Transaction> result = service.getAllTransactionsByGuardianId(UUID.randomUUID(), start, end);

        //Then
        verify(repository, times(1))
                .findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }
}