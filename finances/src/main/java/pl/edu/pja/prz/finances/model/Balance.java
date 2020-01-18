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
import java.util.Objects;
import java.util.UUID;

@Entity
public class Balance extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Balance.class);

    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    @NotNull
    private UUID guardianId;

    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal amount;

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(UUID guardianId) {
        this.guardianId = guardianId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Balance balance = (Balance) o;
        return childId.equals(balance.childId) &&
                guardianId.equals(balance.guardianId) &&
                amount.equals(balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), childId, guardianId, amount);
    }

    @Override
    public String toString() {
        return "[" + childId + "]: " + amount;
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved new balance: " + this);
    }
}
