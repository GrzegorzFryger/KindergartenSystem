package pl.edu.pja.prz.receivables.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTypeTest {

    @Test
    public void Should_ReturnCorrectString() {
        //Given
        PaymentType gotowka = PaymentType.CASH;
        PaymentType przelew = PaymentType.TRANSFER;

        //When
        String result = gotowka.toString();
        String result2 = przelew.toString();

        //Then
        assertEquals("Gotówka", result);
        assertEquals("Przelew", result2);
    }
}
