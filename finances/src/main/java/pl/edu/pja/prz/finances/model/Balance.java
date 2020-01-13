package pl.edu.pja.prz.finances.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Balance extends BaseEntity<Long> implements Serializable {
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID guardianId;

    @Column(columnDefinition="Decimal(10,2) default '0.00'")
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
}
