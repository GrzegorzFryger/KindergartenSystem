package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.payments.model.enums.TypeDiscount;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discount extends BaseEntityLong {
	private String name;
	private String description;
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal value;
	@Enumerated(EnumType.STRING)
	private TypeDiscount typeDiscount;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "discount_recurringPayment",
			joinColumns = { @JoinColumn(name = "fk_discount ") },
			inverseJoinColumns = { @JoinColumn(name = "fk_recurringPayment") })
	private Set<RecurringPayment> recurringPayments = new HashSet<>();

	public Discount() {
		super();
	}

	public Discount(String description, BigDecimal value, TypeDiscount typeDiscount) {
		super();
		this.description = description;
		this.value = value;
		this.typeDiscount = typeDiscount;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setTypeDiscount(TypeDiscount typeDiscount) {
		this.typeDiscount = typeDiscount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Discount)) return false;
		if (!super.equals(o)) return false;

		Discount discount = (Discount) o;

		if (getDescription() != null ? !getDescription().equals(discount.getDescription()) : discount.getDescription() != null)
			return false;
		if (getValue() != null ? !getValue().equals(discount.getValue()) : discount.getValue() != null) return false;
		if (getTypeDiscount() != discount.getTypeDiscount()) return false;
		return getRecurringPayments() != null ? getRecurringPayments().equals(discount.getRecurringPayments()) : discount.getRecurringPayments() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
		result = 31 * result + (getTypeDiscount() != null ? getTypeDiscount().hashCode() : 0);
		result = 31 * result + (getRecurringPayments() != null ? getRecurringPayments().hashCode() : 0);
		return result;
	}
}
