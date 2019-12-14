package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.Status;
import pl.edu.pja.prz.tuition.model.value.PeriodValidity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Tuition extends BaseEntity<Long> implements DiscountCalculator {
	private Child child;
	private BigDecimal amount;
	private Status status;
	private String description;
	private PeriodValidity periodValidity;
	private Set<Rebate> rebates = new HashSet<>();

	Tuition() {
	}

	public Tuition(Child child, BigDecimal amount, Status status, String description, PeriodValidity periodValidity) {
		this.child = child;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.periodValidity = periodValidity;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	void setAmount(BigDecimal amount) {
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

	public Set<Rebate> getRebates() {
		return rebates;
	}

	void setRebates(Set<Rebate> rebates) {
		this.rebates = rebates;
	}

	public void addRebatePolicy(Rebate rebatePolicies) {
		this.rebates.add(rebatePolicies);
	}

	public void removeRebatePolicy(Rebate rebatePolicies) {
		this.rebates.remove(rebatePolicies);
	}


}
