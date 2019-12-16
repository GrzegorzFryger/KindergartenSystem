package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import java.math.BigDecimal;

public class Discount extends BaseEntity<Long> {
	private String description;
	private BigDecimal value;
	private TypeDiscount typeDiscount;

	Discount() {
	}

	public Discount(String description, BigDecimal value, TypeDiscount typeDiscount) {
		this.description = description;
		this.value = value;
		this.typeDiscount = typeDiscount;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public TypeDiscount getTypeDiscount() {
		return typeDiscount;
	}

}
