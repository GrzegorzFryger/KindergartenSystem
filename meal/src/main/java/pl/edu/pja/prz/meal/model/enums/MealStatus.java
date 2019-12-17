package pl.edu.pja.prz.meal.model.enums;

public enum MealStatus {
	ACTIVE("Aktywny"),
	INACTIVE("Nieaktywny");

	private String description;

	MealStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}
