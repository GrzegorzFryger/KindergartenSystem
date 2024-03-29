package pl.edu.pja.prz.receivables.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CashPaymentMapperTest {
    private CashPaymentMapper cashPaymentMapper;
    private CashPayment cashPayment;
    private IncomingPaymentDto dto;

    private LocalDate date;
    private UUID guardianId;
    private UUID childId;

    @BeforeEach
    public void setUp() {
        cashPaymentMapper = new CashPaymentMapperImpl();
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

        //When
        IncomingPaymentDto incomingPaymentDto = cashPaymentMapper.cashPaymentToDto(cashPayment);

        //Then
        verifyDto(incomingPaymentDto);
        assertEquals(PaymentType.CASH, incomingPaymentDto.getPaymentType());
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