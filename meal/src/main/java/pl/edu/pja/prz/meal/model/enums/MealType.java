package pl.edu.pja.prz.meal.model.enums;

public enum MealType {
	BREAKFAST("Śniadanie"),
	DINER("Obiad"),
	SOUP("Zupa"),
	;

	private String description;

	MealType(String description) {
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
