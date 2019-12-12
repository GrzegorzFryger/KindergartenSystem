package pl.edu.pja.prz.receivables.model.builder;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TransactionBuilderTest {
    @Test
    public void Should_Build_Transaction() {
        //Given
        TransactionBuilder builder = new TransactionBuilder();

        //When
        Transaction result = builder
                .withTransactionDate(LocalDate.of(2020, 1, 1))
                .withBookingDate(LocalDate.of(2020, 1, 1))
                .withContractorDetails("Rodzic #001")
                .withTitle("Czesne")
                .withAccountNumber("1234567890")
                .withBankName("TEST BANK")
                .withDetails("WPŁYW")
                .withTransactionNumber("1234")
                .withTransactionAmount(new BigDecimal(50.2))
                .withTransactionCurrency("PLN")
                .build();

        //Then
        assertNotNull(result);
        assertEquals(LocalDate.of(2020, 1, 1), result.getTransactionDate());
        assertEquals(LocalDate.of(2020, 1, 1), result.getBookingDate());
        assertEquals("Czesne", result.getTitle());
        assertEquals(new BigDecimal(50.2), result.getTransactionAmount());
    }
}