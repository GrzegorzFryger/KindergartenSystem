package pl.edu.pja.prz.finances.model.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.finances.model.BalanceHistory;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BalanceHistoryBuilderTest {

    @Test
    public void Should_ThrownException_When_ChildIdIsNull() {
        //Given

        //When
        Assertions.assertThrows(EmptyInputException.class, () -> {
            BalanceHistory result = new BalanceHistoryBuilder().build();
        });

        //Then
    }

    @Test
    public void Should_ThrownException_When_BalanceBeforeChangeIsNull() {
        //Given

        //When
        Assertions.assertThrows(EmptyInputException.class, () -> {
            BalanceHistory result = new BalanceHistoryBuilder()
                    .withChildId(UUID.randomUUID())
                    .build();
        });

        //Then
    }

    @Test
    public void Should_ThrownException_When_AmountOfChangeIsNull() {
        //Given

        //When
        Assertions.assertThrows(EmptyInputException.class, () -> {
            BalanceHistory result = new BalanceHistoryBuilder()
                    .withChildId(UUID.randomUUID())
                    .withBalanceBeforeChange(new BigDecimal("100.23"))
                    .build();
        });

        //Then
    }

    @Test
    public void Should_BuildBalanceHistory_When_AllRequiredFieldsAreInitialized() {
        //Given
        BigDecimal amountOfChange = new BigDecimal("-200.50");
        BigDecimal balanceBeforeChange = new BigDecimal("100.23");
        UUID childId = UUID.randomUUID();

        //When
        BalanceHistory result = new BalanceHistoryBuilder()
                .withAmountOfChange(amountOfChange)
                .withBalanceBeforeChange(balanceBeforeChange)
                .withChildId(childId)
                .build();

        //Then
        assertNotNull(result);
        assertEquals(amountOfChange, result.getAmountOfChange());
        assertEquals(balanceBeforeChange, result.getBalanceBeforeChange());
        assertEquals(childId, result.getChildId());
    }
}