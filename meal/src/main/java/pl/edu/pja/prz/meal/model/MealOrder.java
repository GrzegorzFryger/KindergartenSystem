package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import java.time.LocalDate;

public class MealOrder extends BaseEntityLong {

    private LocalDate orderDate;
    private String mealType;
    private String mealCount;

    public MealOrder(LocalDate orderDate, String mealType, String mealCount) {
        this.orderDate = orderDate;
        this.mealType = mealType;
        this.mealCount = mealCount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealCount() {
        return mealCount;
    }

    public void setMealCount(String mealCount) {
        this.mealCount = mealCount;
    }
}
