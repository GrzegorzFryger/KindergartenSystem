package pl.edu.pja.prz.payments.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.UUID;

@MappedSuperclass
public class Payment extends BaseEntityLong {
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID childId;
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID guardianId;
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
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

	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public UUID getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(UUID guardianId) {
		this.guardianId = guardianId;
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

		if (getChildId() != null ? !getChildId().equals(payment.getChildId()) : payment.getChildId() != null)
			return false;
		if (getGuardianId() != null ? !getGuardianId().equals(payment.getGuardianId()) : payment.getGuardianId() != null)
			return false;
		if (getBaseAmount() != null ? !getBaseAmount().equals(payment.getBaseAmount()) : payment.getBaseAmount() != null)
			return false;
		return getDescription() != null ? getDescription().equals(payment.getDescription()) : payment.getDescription() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getChildId() != null ? getChildId().hashCode() : 0);
		result = 31 * result + (getGuardianId() != null ? getGuardianId().hashCode() : 0);
		result = 31 * result + (getBaseAmount() != null ? getBaseAmount().hashCode() : 0);
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		return result;
	}
}
