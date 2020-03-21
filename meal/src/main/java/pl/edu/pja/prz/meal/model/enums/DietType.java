package pl.edu.pja.prz.meal.model.enums;

public enum DietType {
    VEGETARIAN("Wegetariańska"),
    VEGAN("Wegańska"),
    BASIC("Podstawowa"),
    DIABETIC("Dla Diabetyków"),
    EASY_DIAGESTIBLE("Lekko strawna"),
    NON_DAIRY("Bez nabiału");

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
