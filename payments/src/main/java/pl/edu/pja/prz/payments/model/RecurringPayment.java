package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RecurringPayment extends Payment implements DiscountCalculator {
	private Child child;
	private PeriodValidity periodValidity;
	@ManyToMany(mappedBy = "recurringPayments")
	private Set<Discount> discounts = new HashSet<>();
	private Status status;
	private TypeRecurringPayment recurringPayment;

	public RecurringPayment(Child child, Payment payment, PeriodValidity periodValidity,
	                        TypeRecurringPayment recurringPayment, Status status) {
		this(payment.getAmount(), payment.getDescription(), child, periodValidity, recurringPayment, status);
	}

	public RecurringPayment(BigDecimal amount, String description, Child child, PeriodValidity periodValidity,
	                        TypeRecurringPayment recurringPayment, Status status) {
		super(amount, description);
		this.child = child;
		this.periodValidity = periodValidity;
		this.recurringPayment = recurringPayment;
		this.status = status;
	}

	RecurringPayment() {
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public PeriodValidity getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(PeriodValidity periodValidity) {
		this.periodValidity = periodValidity;
	}

	@Override public Set<Discount> getDiscounts() {
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

	public TypeRecurringPayment getRecurringPayment() {
		return recurringPayment;
	}

	public void setRecurringPayment(TypeRecurringPayment recurringPayment) {
		this.recurringPayment = recurringPayment;
	}

	public void addRebatePolicy(Discount discountPolicies) {
		this.discounts.add(discountPolicies);
	}

	public void removeRebatePolicy(Discount discountPolicies) {
		this.discounts.remove(discountPolicies);
	}

	public void addDiscount(Discount discount ) {
		this.discounts.add(discount);
		discount.getRecurringPayments().add(this);
	}

	public void removeDiscount(Discount discount ) {
		this.discounts.remove(discount);
		discount.getRecurringPayments().remove(this);
	}
}
