package pl.edu.pja.prz.finances.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class BalanceHistory extends BaseEntityLong implements Serializable {
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childId;
    private LocalDate date;
    private BigDecimal balanceBeforeChange;
    private BigDecimal change;

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getBalanceBeforeChange() {
        return balanceBeforeChange;
    }

    public void setBalanceBeforeChange(BigDecimal balanceBeforeChange) {
        this.balanceBeforeChange = balanceBeforeChange;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }
}
