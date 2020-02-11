package pl.edu.pja.prz.meal.model.enums;

public enum DietType {
    VEGETARIAN("Wegetariańska"),
    VEGAN("Wegańska");

    private String description;

    DietType(String description) {
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
