package pl.edu.pja.prz.receivables.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashPaymentMapperTest {
    @Mock
    private CashPaymentMapper cashPaymentMapper;

    @Mock
    private TransactionMapper transactionMapper;

    private Transaction transaction;
    private CashPayment cashPayment;
    private IncomingPaymentDto dto;

    private LocalDate date;
    private UUID guardianId;
    private UUID childId;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        cashPayment = new CashPayment();
        dto = new IncomingPaymentDto();
        guardianId = UUID.randomUUID();
        childId = UUID.randomUUID();
        date = LocalDate.now();

        cashPayment.setTitle("Czesne #001");
        cashPayment.setContractorDetails("Adam XYZ");
        cashPayment.setTransactionAmount(new BigDecimal("50.00"));
        cashPayment.setTransactionCurrency("PLN");
        cashPayment.setTransactionDate(date);
        cashPayment.setChildId(childId);
        cashPayment.setGuardianId(guardianId);

        transaction.setTitle("Czesne #001");
        transaction.setContractorDetails("Adam XYZ");
        transaction.setTransactionAmount(new BigDecimal("50.00"));
        transaction.setTransactionCurrency("PLN");
        transaction.setTransactionDate(date);
        transaction.setChildId(childId);
        transaction.setGuardianId(guardianId);

        dto.setTitle("Czesne #001");
        dto.setContractorDetails("Adam XYZ");
        dto.setTransactionAmount(new BigDecimal("50.00"));
        dto.setTransactionCurrency("PLN");
        dto.setTransactionDate(date);
        dto.setChildId(childId);
        dto.setGuardianId(guardianId);
    }

    @Test
    public void Should_MapCashPayment() {
        //Given
        dto.setPaymentType(PaymentType.CASH);
        when(cashPaymentMapper.cashPaymentToDto(any(CashPayment.class))).thenReturn(dto);

        //When
        IncomingPaymentDto incomingPaymentDto = cashPaymentMapper.cashPaymentToDto(cashPayment);

        //Then
        verifyDto(incomingPaymentDto);
        assertEquals(PaymentType.CASH, incomingPaymentDto.getPaymentType());
    }

    @Test
    public void Should_MapTransaction() {
        //Given
        dto.setPaymentType(PaymentType.TRANSFER);
        when(transactionMapper.transactionToDto(any(Transaction.class))).thenReturn(dto);

        //When
        IncomingPaymentDto incomingPaymentDto = transactionMapper.transactionToDto(transaction);

        //Then
        verifyDto(incomingPaymentDto);
        assertEquals(PaymentType.TRANSFER, incomingPaymentDto.getPaymentType());
    }

    private void verifyDto(IncomingPaymentDto dto) {
        assertNotNull(dto);
        assertEquals("Czesne #001", dto.getTitle());
        assertEquals("Adam XYZ", dto.getContractorDetails());
        assertEquals(new BigDecimal("50.00"), dto.getTransactionAmount());
        assertEquals("PLN", dto.getTransactionCurrency());
        assertEquals(date, dto.getTransactionDate());
        assertEquals(guardianId, dto.getGuardianId());
        assertEquals(childId, dto.getChildId());
    }
}