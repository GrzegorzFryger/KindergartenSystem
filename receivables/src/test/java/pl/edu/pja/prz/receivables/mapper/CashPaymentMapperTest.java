package pl.edu.pja.prz.receivables.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CashPaymentMapperTest {
    private Transaction transaction;
    private CashPayment cashPayment;

    private LocalDate date;
    private UUID guardianId;
    private UUID childId;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        cashPayment = new CashPayment();
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
    }

    @Test
    public void Should_MapCashPayment() {
        //Given

        //When
        IncomingPaymentDto dto = CashPaymentMapper.INSTANCE.cashPaymentToDto(cashPayment);

        //Then
        verifyDto(dto);
        assertEquals(PaymentType.CASH, dto.getPaymentType());
    }

    @Test
    public void Should_MapTransaction() {
        //Given

        //When
        IncomingPaymentDto dto = TransactionMapper.INSTANCE.transactionToDto(transaction);

        //Then
        verifyDto(dto);
        assertEquals(PaymentType.TRANSFER, dto.getPaymentType());
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