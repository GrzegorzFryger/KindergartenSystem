package pl.edu.pja.prz.meal.model.dto;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealCreateUpdateDTO {

    private double mealPrice;
    private MealType mealType;
    private long childID;
    private LocalDate mealFromDate;
    private LocalDate mealToDate;

    public MealCreateUpdateDTO(double mealPrice, MealType mealType, long childID, LocalDate mealFromDate, LocalDate mealToDate) {
        this.mealPrice = mealPrice;
        this.mealType = mealType;
        this.childID = childID;
        this.mealFromDate = mealFromDate;
        this.mealToDate = mealToDate;
    }

    public static Meal createMealFactory(MealCreateUpdateDTO dto) {
        return new Meal(dto.getMealPrice(),
                LocalDateTime.of(dto.getMealFromDate(), LocalTime.NOON),
                        LocalDateTime.of(dto.getMealToDate(), LocalTime.MIDNIGHT),
                                MealStatus.ACTIVE,
                                dto.getMealType(),
                                dto.getChildID());
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public LocalDate getMealFromDate() {
        return mealFromDate;
    }

    public LocalDate getMealToDate() {
        return mealToDate;
    }

    public MealType getMealType() {
        return mealType;
    }

    public long getChildID() {
        return childID;
    }
}
