package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import java.math.BigDecimal;


public class Payment extends BaseEntityLong {
	private BigDecimal baseAmount;
	private String description;

	Payment() {
		super();
	}

	public Payment(BigDecimal baseAmount, String description) {
		super();
		this.baseAmount = baseAmount;
		this.description = description;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
