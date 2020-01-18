package pl.edu.pja.prz.finances.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.finances.model.builder.BalanceHistoryBuilder;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BalanceHistoryTest {
    private BalanceHistory balanceHistory;
    private BalanceHistory balanceHistory2;

    @BeforeEach
    public void setUp() {
        BalanceHistoryBuilder builder = new BalanceHistoryBuilder();

        balanceHistory = builder
                .withAmountOfChange(new BigDecimal("-200.50"))
                .withBalanceBeforeChange(new BigDecimal("100.23"))
                .withChildId(UUID.randomUUID())
                .withTitle("PAYMENT")
                .build();

        balanceHistory2 = builder.build();
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_Property_Is_Changed() {
        //Given
        int beforeChange = balanceHistory.hashCode();

        //When
        balanceHistory.setBalanceBeforeChange(new BigDecimal("25.00"));
        int afterChange = balanceHistory.hashCode();

        //Then
        assertNotEquals(beforeChange, afterChange);
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int first = balanceHistory.hashCode();
        int second = balanceHistory2.hashCode();

        //Then
        assertEquals(first, second);

    }

    @Test
    public void Should_ReturnTrue_When_ObjectsAreSymmetricallyEqual() {
        //Given

        //When
        boolean firstEqualsToSecond = balanceHistory.equals(balanceHistory2);
        boolean secondEqualsToFirst = balanceHistory2.equals(balanceHistory);

        //Then
        assertTrue(firstEqualsToSecond);
        assertTrue(secondEqualsToFirst);
    }
}