package pl.edu.pja.prz.receivables.service;

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

class DtoMappingServiceImplTest {
    private Transaction transaction;
    private CashPayment cashPayment;
    private DtoMappingServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new DtoMappingServiceImpl();

        transaction = new Transaction();
        cashPayment = new CashPayment();

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
    }

    @Test
    public void Should_MapCashPayment() {
        //Given

        //When
        IncomingPaymentDto dto = service.fromCashPayment(cashPayment);

        //Then
        assertNotNull(dto);
        assertEquals(PaymentType.CASH, dto.getPaymentType());
        assertEquals("Czesne #001", dto.getTitle());
    }

    @Test
    public void Should_MapTransaction() {
        //Given

        //When
        IncomingPaymentDto dto = service.fromTransaction(transaction);

        //Then
        assertNotNull(dto);
        assertEquals(PaymentType.TRANSFER, dto.getPaymentType());
        assertEquals("Czesne #001", dto.getTitle());
    }
}