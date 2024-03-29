package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.receivables.mapper.CashPaymentMapper;
import pl.edu.pja.prz.receivables.mapper.TransactionMapper;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomingPaymentsServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CashPaymentRepository cashPaymentRepository;

    @Mock
    private CashPaymentMapper cashPaymentMapper;

    @Mock
    private TransactionMapper transactionMapper;

    private List<Transaction> transactions;
    private List<CashPayment> cashPayments;
    private IncomingPaymentsServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new IncomingPaymentsServiceImpl(transactionRepository, cashPaymentRepository,
                cashPaymentMapper, transactionMapper);

        transactions = new ArrayList<>();
        cashPayments = new ArrayList<>();

        Transaction transaction = new Transaction();
        CashPayment cashPayment = new CashPayment();

        cashPayment.setTitle("Czesne #001");
        cashPayment.setContractorDetails("Adam XYZ");
        cashPayment.setTransactionAmount(new BigDecimal("50.00"));
        cashPayment.setTransactionCurrency("PLN");
        cashPayment.setTransactionDate(LocalDate.now());
        cashPayment.setChildId(UUID.randomUUID());
        cashPayment.setGuardianId(UUID.randomUUID());

        transaction.setTitle("Czesne #001");
        transaction.setContractorDetails("Adam XYZ");
        transaction.setTransactionAmount(new BigDecimal("50.00"));
        transaction.setTransactionCurrency("PLN");
        transaction.setTransactionDate(LocalDate.now());
        transaction.setChildId(UUID.randomUUID());
        transaction.setGuardianId(UUID.randomUUID());

        transactions.add(transaction);
        cashPayments.add(cashPayment);
    }

    @Test
    public void Should_MapAllTransactionsForGivenGuardianId() {
        //Given

        //When
        when(cashPaymentRepository.findAllByGuardianId(any(UUID.class))).thenReturn(cashPayments);
        when(transactionRepository.findAllByGuardianId(any(UUID.class))).thenReturn(transactions);
        List<IncomingPaymentDto> dtos = service.getAllPaymentsForGuardian(UUID.randomUUID());

        //Then
        assertEquals(2, dtos.size());
        verify(cashPaymentRepository, times(1)).findAllByGuardianId(any(UUID.class));
        verify(transactionRepository, times(1)).findAllByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_MapAllTransactionsForGivenChildId() {
        //Given

        //When
        when(cashPaymentRepository.findAllByChildId(any(UUID.class))).thenReturn(cashPayments);
        when(transactionRepository.findAllByChildId(any(UUID.class))).thenReturn(transactions);
        List<IncomingPaymentDto> dtos = service.getAllPaymentsForChild(UUID.randomUUID());

        //Then
        assertEquals(2, dtos.size());
        verify(cashPaymentRepository, times(1)).findAllByChildId(any(UUID.class));
        verify(transactionRepository, times(1)).findAllByChildId(any(UUID.class));
    }

    @Test
    public void Should_MapAllTransactionsForGivenGuardianIdBetweenDates() {
        //Given
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        //When
        when(cashPaymentRepository.findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(cashPayments);
        when(transactionRepository.findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);
        List<IncomingPaymentDto> dtos = service.getAllPaymentsForGuardian(UUID.randomUUID(), start, end);

        //Then
        assertEquals(2, dtos.size());
        verify(cashPaymentRepository, times(1))
                .findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
        verify(transactionRepository, times(1))
                .findAllByGuardianIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_MapAllTransactionsForGivenChildIdBetweenDates() {
        //Given
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        //When
        when(cashPaymentRepository.findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(cashPayments);
        when(transactionRepository.findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);
        List<IncomingPaymentDto> dtos = service.getAllPaymentsForChild(UUID.randomUUID(), start, end);

        //Then
        assertEquals(2, dtos.size());
        verify(cashPaymentRepository, times(1))
                .findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
        verify(transactionRepository, times(1))
                .findAllByChildIdBetweenDates(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }
}