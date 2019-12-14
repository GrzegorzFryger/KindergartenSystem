package pl.edu.pja.prz.tuition.model;

import pl.edu.pja.prz.tuition.model.enums.TypeRebate;

import java.math.BigDecimal;

public class Rebate extends BaseEntity<Long> {
	private String description;
	private BigDecimal value;
	private TypeRebate typeRebate;

	Rebate() {
	}

	public Rebate(String description, BigDecimal value, TypeRebate typeRebate) {
		this.description = description;
		this.value = value;
		this.typeRebate = typeRebate;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public TypeRebate getTypeRebate() {
		return typeRebate;
	}

}
