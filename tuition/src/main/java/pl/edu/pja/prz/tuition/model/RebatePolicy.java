package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.TypeRebate;

import java.math.BigDecimal;

public class RebatePolicy {
	private Long id;
	private String description;
	private BigDecimal value;
	private TypeRebate typeRebate;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public TypeRebate getTypeRebate() {
		return typeRebate;
	}

	public void setTypeRebate(TypeRebate typeRebate) {
		this.typeRebate = typeRebate;
	}



}
