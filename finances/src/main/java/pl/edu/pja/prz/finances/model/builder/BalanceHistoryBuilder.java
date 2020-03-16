package pl.edu.pja.prz.finances.model.builder;

import org.apache.commons.lang3.StringUtils;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BalanceHistoryBuilder {
    private UUID childId;
    private BigDecimal amountOfChange;
    private String title;
    private OperationType operationType;

    public BalanceHistoryBuilder() {

    }

    public BalanceHistoryBuilder withChildId(UUID childId) {
        this.childId = childId;
        return this;
    }

    public BalanceHistoryBuilder withAmountOfChange(BigDecimal amountOfChange) {
        this.amountOfChange = amountOfChange;
        return this;
    }

    public BalanceHistoryBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BalanceHistoryBuilder withOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public BalanceHistory build() {
        BalanceHistory balanceHistory = new BalanceHistory();
        if (childId == null) {
            throw new EmptyInputException("UUID", "Child Id");
        }
        if (amountOfChange == null) {
            throw new EmptyInputException("Big Decimal", "Amount of change");
        }
        if (title == null || StringUtils.isEmpty(title)) {
            throw new EmptyInputException("String", "title");
        }
        if (operationType == null) {
            throw new EmptyInputException("OperationType", "operationType");
        }

        balanceHistory.setChildId(childId);
        balanceHistory.setAmountOfChange(amountOfChange);
        balanceHistory.setTitle(title);
        balanceHistory.setOperationType(operationType);
        balanceHistory.setDate(LocalDate.now());

        return balanceHistory;
    }
}
