package pl.edu.pja.prz.payments.model.dto;

import pl.edu.pja.prz.finances.model.enums.OperationType;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentHistoryDto {
    private Long id;
    private LocalDate date;
    private String description;
    private UUID childId;
    private UUID guardianId;
    private TypeRecurringPayment typeRecurringPayment;
    private OperationType operationType;

    public PaymentHistoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
