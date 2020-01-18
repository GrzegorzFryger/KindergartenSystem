package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import java.math.BigDecimal;

public class Payment extends BaseEntityLong {
	private BigDecimal amount;
	private String description;

	Payment() {
		super();
	}

	public Payment(BigDecimal amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
