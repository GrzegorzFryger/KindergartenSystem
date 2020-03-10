package pl.edu.pja.prz.finances.model.dto;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal amount = BigDecimal.ZERO;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
