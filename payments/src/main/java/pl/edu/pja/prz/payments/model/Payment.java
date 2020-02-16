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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Payment)) return false;
		if (!super.equals(o)) return false;

		Payment payment = (Payment) o;

		if (getBaseAmount() != null ? !getBaseAmount().equals(payment.getBaseAmount()) : payment.getBaseAmount() != null)
			return false;
		return getDescription() != null ? getDescription().equals(payment.getDescription()) : payment.getDescription() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getBaseAmount() != null ? getBaseAmount().hashCode() : 0);
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		return result;
	}
}
