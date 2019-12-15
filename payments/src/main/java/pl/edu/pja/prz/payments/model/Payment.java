package pl.edu.pja.prz.payments.model;

import java.math.BigDecimal;

public class Payment extends BaseEntity<Long>  {
	private BigDecimal amount;
	private String description;

	Payment() {
	}

	public Payment(BigDecimal amount, String description) {
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
