package pl.edu.pja.prz.payments.model.dto;

import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import java.math.BigDecimal;

public class DiscountDto {
    private String name;
    private String description;
    private BigDecimal value;
    private TypeDiscount typeDiscount;

    public DiscountDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TypeDiscount getTypeDiscount() {
        return typeDiscount;
    }

    public void setTypeDiscount(TypeDiscount typeDiscount) {
        this.typeDiscount = typeDiscount;
    }
}
