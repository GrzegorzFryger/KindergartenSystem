package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;

@Entity
public class MealPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private double mealPrice;

    public MealPrice() {
    }

    public MealPrice(long id,MealType mealType, double mealPrice ) {
        this.id = id;
        this.mealType = mealType;
        this.mealPrice = mealPrice;
    }

    public long getId() {
        return id;
    }

    public MealType getMealType() {
        return mealType;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }
}
