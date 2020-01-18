package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discount extends BaseEntityLong {
	private String description;
	private BigDecimal value;
	private TypeDiscount typeDiscount;
	@ManyToMany
	@JoinTable(name = "discount_recurringPayment",
			joinColumns = { @JoinColumn(name = "fk_discount ") },
			inverseJoinColumns = { @JoinColumn(name = "fk_recurringPayment") })
	private Set<RecurringPayment> recurringPayments = new HashSet<>();

	Discount() {
		super();
	}

	public Discount(String description, BigDecimal value, TypeDiscount typeDiscount) {
		super();
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

	public Set<RecurringPayment> getRecurringPayments() {
		return recurringPayments;
	}

	public void setRecurringPayments(Set<RecurringPayment> recurringPayments) {
		this.recurringPayments = recurringPayments;
	}
}
