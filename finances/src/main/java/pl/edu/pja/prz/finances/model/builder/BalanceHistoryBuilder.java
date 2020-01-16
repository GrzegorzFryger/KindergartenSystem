package pl.edu.pja.prz.finances.model.builder;

import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.finances.model.BalanceHistory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BalanceHistoryBuilder {
    private UUID childId;
    private BigDecimal balanceBeforeChange;
    private BigDecimal amountOfChange;

    public BalanceHistoryBuilder() {

    }

    public BalanceHistoryBuilder withChildId(UUID childId) {
        this.childId = childId;
        return this;
    }

    public BalanceHistoryBuilder withBalanceBeforeChange(BigDecimal balanceBeforeChange) {
        this.balanceBeforeChange = balanceBeforeChange;
        return this;
    }

    public BalanceHistoryBuilder withAmountOfChange(BigDecimal amountOfChange) {
        this.amountOfChange = amountOfChange;
        return this;
    }

    public BalanceHistory build() {
        BalanceHistory balanceHistory = new BalanceHistory();
        if (childId == null) {
            throw new EmptyInputException("UUID", "Child Id");
        }
        if (balanceBeforeChange == null) {
            throw new EmptyInputException("Big Decimal", "Balance before change");
        }
        if (amountOfChange == null) {
            throw new EmptyInputException("Big Decimal", "Amount of change");
        }

        balanceHistory.setChildId(childId);
        balanceHistory.setBalanceBeforeChange(balanceBeforeChange);
        balanceHistory.setAmountOfChange(amountOfChange);
        balanceHistory.setDate(LocalDate.now());

        return balanceHistory;
    }
}
