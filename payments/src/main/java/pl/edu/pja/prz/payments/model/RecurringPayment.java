package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RecurringPayment extends Payment implements DiscountCalculator {
	private PeriodValidity periodValidity;
	@ManyToMany(mappedBy = "recurringPayments", fetch = FetchType.EAGER)
	private Set<Discount> discounts = new HashSet<>();
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private TypeRecurringPayment typeRecurringPayment;

	public RecurringPayment() {
		super();
	}


	public PeriodValidity getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(PeriodValidity periodValidity) {
		this.periodValidity = periodValidity;
	}

	@Override
	public Set<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Set<Discount> discounts) {
		this.discounts = discounts;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TypeRecurringPayment getTypeRecurringPayment() {
		return typeRecurringPayment;
	}

	public void setTypeRecurringPayment(TypeRecurringPayment typeRecurringPayment) {
		this.typeRecurringPayment = typeRecurringPayment;
	}

	public void addRebatePolicy(Discount discountPolicies) {
		this.discounts.add(discountPolicies);
	}

	public void removeRebatePolicy(Discount discountPolicies) {
		this.discounts.remove(discountPolicies);
	}

	public void addDiscount(Discount discount) {
		this.discounts.add(discount);
		discount.getRecurringPayments().add(this);
	}

	public void removeDiscount(Discount discount) {
		this.discounts.remove(discount);
		discount.getRecurringPayments().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RecurringPayment)) return false;
		if (!super.equals(o)) return false;

		RecurringPayment that = (RecurringPayment) o;

		if (getPeriodValidity() != null ? !getPeriodValidity().equals(that.getPeriodValidity()) : that.getPeriodValidity() != null)
			return false;
		if (getDiscounts() != null ? !getDiscounts().equals(that.getDiscounts()) : that.getDiscounts() != null)
			return false;
		if (getStatus() != that.getStatus()) return false;
		return getTypeRecurringPayment() == that.getTypeRecurringPayment();
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getPeriodValidity() != null ? getPeriodValidity().hashCode() : 0);
		result = 31 * result + (getDiscounts() != null ? getDiscounts().hashCode() : 0);
		result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
		result = 31 * result + (getTypeRecurringPayment() != null ? getTypeRecurringPayment().hashCode() : 0);
		return result;
	}
}
