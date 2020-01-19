package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MealPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private BigDecimal mealPrice;

    public MealPrice() {
    }

    public MealPrice(long id,MealType mealType, BigDecimal mealPrice ) {
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

    public BigDecimal getMealPrice() {
        return mealPrice;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void setMealPrice(BigDecimal mealPrice) {
        this.mealPrice = mealPrice;
    }
}
