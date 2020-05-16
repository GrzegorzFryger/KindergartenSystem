package pl.edu.pja.prz.payments.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.finances.model.enums.OperationType;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class PaymentHistory extends BaseEntityLong {
    private LocalDate date;
    private String description;
    private BigDecimal amount;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID guardianId;
    @Enumerated(EnumType.STRING)
    private TypeRecurringPayment typeRecurringPayment;
    private OperationType operationType;

    public PaymentHistory() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public TypeRecurringPayment getTypeRecurringPayment() {
        return typeRecurringPayment;
    }

    public void setTypeRecurringPayment(TypeRecurringPayment typeRecurringPayment) {
        this.typeRecurringPayment = typeRecurringPayment;
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
        if (!(o instanceof PaymentHistory)) return false;
        if (!super.equals(o)) return false;

        PaymentHistory that = (PaymentHistory) o;

        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getChildId() != null ? !getChildId().equals(that.getChildId()) : that.getChildId() != null) return false;
        if (getGuardianId() != null ? !getGuardianId().equals(that.getGuardianId()) : that.getGuardianId() != null)
            return false;
        if (getTypeRecurringPayment() != that.getTypeRecurringPayment()) return false;
        return getOperationType() == that.getOperationType();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getChildId() != null ? getChildId().hashCode() : 0);
        result = 31 * result + (getGuardianId() != null ? getGuardianId().hashCode() : 0);
        result = 31 * result + (getTypeRecurringPayment() != null ? getTypeRecurringPayment().hashCode() : 0);
        result = 31 * result + (getOperationType() != null ? getOperationType().hashCode() : 0);
        return result;
    }
}
