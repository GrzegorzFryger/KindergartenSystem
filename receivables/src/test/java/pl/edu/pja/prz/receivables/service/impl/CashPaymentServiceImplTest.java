package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.service.impl.CashPaymentServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CashPaymentServiceImplTest {
    private CashPayment payment;

    @Mock
    CashPaymentRepository repository;

    CashPaymentServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new CashPaymentServiceImpl(repository);

        payment = new CashPayment();
        payment.setContractorDetails("Adam Smith");
        payment.setTransactionDate(LocalDate.now());
        payment.setTransactionCurrency("PLN");
        payment.setTransactionAmount(new BigDecimal("50.00"));
        payment.setTitle("Czesne #001");
        payment.setId(1L);
    }

    @Test
    public void Should_SaveCashPayment() {
        //Given

        //When
        service.save(payment);

        //Then
        verify(repository, times(1)).save(any(CashPayment.class));
    }

    @Test
    public void Should_Not_SaveCashPayment_When_TransactionAmountIsNegative() {
        //Given
        payment.setTransactionAmount(new BigDecimal("-0.01"));

        //When
        service.save(payment);

        //Then
        verify(repository, never()).save(any(CashPayment.class));
    }

    @Test
    public void Should_GetAllCashPayments() {
        //Given
        List<CashPayment> cashPayments = new ArrayList<>();
        cashPayments.add(payment);

        //When
        when(repository.findAll()).thenReturn(cashPayments);
        List<CashPayment> result = service.getAllCashPayments();

        //Then
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void Should_DeleteCashPayment() {
        //Given

        //When
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(payment));
        service.delete(1L);

        //Then
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    public void Should_ThrowException_When_CashPaymentToDeleteDoesNotExists() {
        //Given

        //When
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.delete(1L);
        });

        //Then
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    public void Should_UpdateCashPayment() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(payment));

        //When
        service.update(payment);

        //Then
        verify(repository, times(1)).save(any(CashPayment.class));
    }

    @Test
    public void Should_ThrowException_When_CashPaymentToUpdateDoesNotExist() {
        //Given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //When
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            service.update(payment);
        });

        //Then
        verify(repository, never()).save(any(CashPayment.class));
    }

}