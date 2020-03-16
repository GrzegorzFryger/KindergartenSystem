package pl.edu.pja.prz.finances.model.dto;

import java.math.BigDecimal;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.sum;

public class Balance {
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal receivables = BigDecimal.ZERO;
    private BigDecimal liabilities = BigDecimal.ZERO;

    public Balance() {

    }

    public Balance(BigDecimal receivables, BigDecimal liabilities) {
        this.receivables = receivables;
        this.liabilities = liabilities;
        this.balance = sum(receivables, liabilities);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public void setReceivables(BigDecimal receivables) {
        this.receivables = receivables;
    }

    public BigDecimal getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(BigDecimal liabilities) {
        this.liabilities = liabilities;
    }
}
