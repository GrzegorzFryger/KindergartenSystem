package pl.edu.pja.prz.receivables.model.builder;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionBuilder {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

    public TransactionBuilder() {

    }

    public TransactionBuilder withTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public TransactionBuilder withBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public TransactionBuilder withContractorDetails(String contractorDetails) {
        this.contractorDetails = contractorDetails;
        return this;
    }

    public TransactionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TransactionBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public TransactionBuilder withBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public TransactionBuilder withDetails(String details) {
        this.details = details;
        return this;
    }

    public TransactionBuilder withTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    public TransactionBuilder withTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public TransactionBuilder withTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
        return this;
    }

    public TransactionBuilder fromCSVRecord(CSVRecord csvRecord) {
        transactionDate = parseDate(csvRecord.get(0));
        bookingDate = parseDate(csvRecord.get(1));
        contractorDetails = csvRecord.get(2);
        title = csvRecord.get(3);
        accountNumber = csvRecord.get(4);
        bankName = csvRecord.get(5);
        details = csvRecord.get(6);
        transactionNumber = csvRecord.get(7);
        if (StringUtils.isNotEmpty(csvRecord.get(8))) {
            transactionAmount = new BigDecimal(csvRecord.get(8).replace(',', '.'));
        }
        transactionCurrency = csvRecord.get(9);
        return this;
    }

    private LocalDate parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            return LocalDate.parse(date, formatter);
        }
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setBookingDate(bookingDate);
        transaction.setContractorDetails(contractorDetails);
        transaction.setTitle(title);
        transaction.setAccountNumber(accountNumber);
        transaction.setBankName(bankName);
        transaction.setDetails(details);
        transaction.setTransactionNumber(transactionNumber);
        transaction.setTransactionAmount(transactionAmount);
        transaction.setTransactionCurrency(transactionCurrency);
        return transaction;
    }
}
