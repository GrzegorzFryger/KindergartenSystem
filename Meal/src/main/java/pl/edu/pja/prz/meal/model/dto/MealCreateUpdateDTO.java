package pl.edu.pja.prz.meal.model.dto;

import pl.edu.pja.prz.meal.model.enums.MealType;

public class MealCreateUpdateDTO {

    private double mealPrice;
    private MealType mealType;
    private long childID;

    public MealCreateUpdateDTO(double mealPrice, MealType mealType, long childID) {
        this.mealPrice = mealPrice;
        this.mealType = mealType;
        this.childID = childID;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public MealType getMealType() {
        return mealType;
    }

    public long getChildID() {
        return childID;
    }
}
