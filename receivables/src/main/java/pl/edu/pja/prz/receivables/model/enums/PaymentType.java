package pl.edu.pja.prz.receivables.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
    CASH("Gotówka"),
    TRANSFER("Przelew");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
