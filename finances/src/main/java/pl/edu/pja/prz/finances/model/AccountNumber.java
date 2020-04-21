package pl.edu.pja.prz.finances.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class AccountNumber extends BaseEntityLong implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(AccountNumber.class);

    @NotNull
    @Column(length = 26)
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountNumber that = (AccountNumber) o;
        return accountNumber.equals(that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountNumber);
    }

    @Override
    public String toString() {
        return "AccountNumber [" + accountNumber + "]";
    }

    @PostPersist
    public void postPersist() {
        logger.info("Saved new account number: {}", this);
    }
}