package pl.edu.pja.prz.receivables.model;


import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    private LocalDate transactionDate;
    private LocalDate bookingDate;
    private String contractorDetails;
    private String title;
    private String accountNumber;
    private String bankName;
    private String details;
    private String transactionNumber;
    private BigDecimal transactionAmount;
    private String transactionCurrency;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
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
}
