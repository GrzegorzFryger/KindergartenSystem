package pl.edu.pja.prz.receivables.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {
    private CashPayment payment;
    private CashPayment payment2;

    @BeforeEach
    public void setUp() {
        payment = new CashPayment();
        payment2 = new CashPayment();

        payment.setTitle("Czesne #001");
        payment2.setTitle("Czesne #001");

        payment.setTransactionAmount(new BigDecimal("20.00"));
        payment2.setTransactionAmount(new BigDecimal("20.00"));

        payment.setTransactionCurrency("PLN");
        payment2.setTransactionCurrency("PLN");

        LocalDate date = LocalDate.now();
        payment.setTransactionDate(date);
        payment2.setTransactionDate(date);

        payment.setContractorDetails("Adam XYZ");
        payment2.setContractorDetails("Adam XYZ");
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = payment.hashCode();

        //When
        payment.setTransactionCurrency("EUR");
        int afterChange = payment.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = payment.hashCode();
        int second = payment2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = payment.equals(payment2);
        boolean secondEqualsToFirst = payment2.equals(payment);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }

    @Test
    public void Should_ReturnProperString() {
        //Given

        //When
        String result = payment.toString();

        //Then
        assertEquals("Czesne #001 [20.00 PLN]", result);
    }
}
