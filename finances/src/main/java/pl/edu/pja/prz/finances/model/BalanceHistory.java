package pl.edu.pja.prz.finances.model;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BalanceHistory extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(BalanceHistory.class);

    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID childId;
    @NotNull
    private LocalDate date;
    @NotNull
    private BigDecimal balanceBeforeChange;
    @NotNull
    private BigDecimal amountOfChange;
    @NotNull
    private String title;

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

    public BigDecimal getAmountOfChange() {
        return amountOfChange;
    }

    public void setAmountOfChange(BigDecimal change) {
        this.amountOfChange = change;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BalanceHistory that = (BalanceHistory) o;
        return childId.equals(that.childId) &&
                date.equals(that.date) &&
                balanceBeforeChange.equals(that.balanceBeforeChange) &&
                amountOfChange.equals(that.amountOfChange) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), childId, date, balanceBeforeChange, amountOfChange, title);
    }

    @Override
    public String toString() {
        return "[" + childId + "] - from: " + balanceBeforeChange + " (" + amountOfChange + ")";
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved balance change: " + this);
    }
}
