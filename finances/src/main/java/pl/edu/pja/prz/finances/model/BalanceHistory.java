package pl.edu.pja.prz.finances.model;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.finances.model.enums.OperationType;

import javax.persistence.*;
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
    private BigDecimal amountOfChange;
    @NotNull
    private String title;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

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

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BalanceHistory that = (BalanceHistory) o;
        return childId.equals(that.childId) &&
                date.equals(that.date) &&
                amountOfChange.equals(that.amountOfChange) &&
                title.equals(that.title) &&
                operationType.equals(that.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), childId, date, amountOfChange, title, operationType);
    }

    @Override
    public String toString() {
        return "[" + childId + "] - Balance changed by: (" + amountOfChange + ")";
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved balance change: {}", this);
    }
}
