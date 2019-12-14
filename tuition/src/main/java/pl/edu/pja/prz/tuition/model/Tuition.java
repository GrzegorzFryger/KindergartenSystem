package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.Status;
import pl.edu.pja.prz.tuition.model.value.PeriodValidity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Tuition implements DiscountCalculator {
	private Long id;
	private Child child;
	private BigDecimal amount;
	private Status status;
	private String description;
	private PeriodValidity periodValidity;
	private Set<RebatePolicy> rebatePolicy = new HashSet<>();

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PeriodValidity getPeriodValidity() {
		return periodValidity;
	}

	public void setPeriodValidity(PeriodValidity periodValidity) {
		this.periodValidity = periodValidity;
	}

	public Set<RebatePolicy> getRebatePolicy() {
		return rebatePolicy;
	}

	public void setRebatePolicy(Set<RebatePolicy> rebatePolicy) {
		this.rebatePolicy = rebatePolicy;
	}


}
