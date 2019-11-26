package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double mealPrice;
    private LocalDate mealDate;
    private MealType mealType;
    private long childID;


    public Meal() {
    }

    public Meal(double mealPrice, LocalDate mealDate, MealType mealType, long childID) {
        this.mealPrice = mealPrice;
        this.mealDate = mealDate;
        this.mealType = mealType;
        this.childID = childID;
    }

    public Long getId() {
        return id;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public MealType getMealType() {
        return mealType;
    }

    public long getChildID() {
        return childID;
    }
}
