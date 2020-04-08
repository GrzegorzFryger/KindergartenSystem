package pl.edu.pja.prz.receivables.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.receivables.model.builder.TransactionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Transaction transaction;
    private Transaction transaction2;

    @BeforeEach
    public void setUp() {
        TransactionBuilder builder = new TransactionBuilder()
                .withTransactionDate(LocalDate.of(2020, 1, 1))
                .withBookingDate(LocalDate.of(2020, 1, 1))
                .withContractorDetails("Rodzic #001")
                .withTitle("Czesne")
                .withAccountNumber("1234567890")
                .withBankName("TEST BANK")
                .withDetails("WPŁYW")
                .withTransactionNumber("1234")
                .withTransactionAmount(new BigDecimal(25.5))
                .withTransactionCurrency("PLN");

        transaction = builder.build();
        transaction2 = builder.build();
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = transaction.hashCode();

        //When
        transaction.setTransactionCurrency("EUR");
        int afterChange = transaction.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = transaction.hashCode();
        int second = transaction2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = transaction.equals(transaction2);
        boolean secondEqualsToFirst = transaction2.equals(transaction);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnProperString() {
        //Given

        //When
        String result = transaction.toString();

        //Then
        assertEquals("Czesne [25.5 PLN]", result);
    }
}
