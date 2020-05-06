package pl.edu.pja.prz.payments.model.dto;

import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RecurringPaymentDto {
	private Long id;
	private BigDecimal amount;
	private String description;
	private UUID child;
	private UUID guardian;
	private LocalDate startDate;
	private LocalDate endDate;
	private TypeRecurringPayment type;

	public RecurringPaymentDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UUID getChild() {
		return child;
	}

	public void setChild(UUID child) {
		this.child = child;
	}

	public UUID getGuardian() {
		return guardian;
	}

	public void setGuardian(UUID guardian) {
		this.guardian = guardian;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public TypeRecurringPayment getType() {
		return type;
	}

	public void setType(TypeRecurringPayment type) {
		this.type = type;
	}
}
