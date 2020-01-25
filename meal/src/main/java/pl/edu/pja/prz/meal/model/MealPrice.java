package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MealPrice extends BaseEntityLong {

    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private BigDecimal mealPrice;

    public MealPrice() {
    }

    public MealPrice(MealType mealType, BigDecimal mealPrice) {
        this.mealType = mealType;
        this.mealPrice = mealPrice;
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
