package pl.edu.pja.prz.receivables.model;

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
public class CashPayment extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CashPayment.class);

    @NotNull
    private LocalDate transactionDate;
    @NotNull
    private String contractorDetails;
    @NotNull
    private String title;
    @NotNull
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal transactionAmount;
    @NotNull
    private String transactionCurrency;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childId;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID guardianId;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getContractorDetails() {
        return contractorDetails;
    }

    public void setContractorDetails(String contractorDetails) {
        this.contractorDetails = contractorDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashPayment that = (CashPayment) o;
        return transactionDate.equals(that.transactionDate) &&
                contractorDetails.equals(that.contractorDetails) &&
                title.equals(that.title) &&
                transactionAmount.equals(that.transactionAmount) &&
                transactionCurrency.equals(that.transactionCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, contractorDetails, title, transactionAmount, transactionCurrency);
    }

    @Override
    public String toString() {
        return title + " [" + transactionAmount + " " + transactionCurrency + "]";
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved cash payment: {}", this);
    }
}
