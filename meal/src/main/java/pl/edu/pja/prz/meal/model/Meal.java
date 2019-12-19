package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal mealPrice;
    private LocalDateTime mealFromDate;
    private LocalDateTime mealToDate;
    private MealStatus mealStatus;
    @Enumerated(EnumType.STRING)
    private List<MealType> mealTypes = new ArrayList<>();
    private UUID childID;


    public Meal() {
    }

    public Meal(BigDecimal mealPrice, LocalDateTime mealFromDate, LocalDateTime mealToDate,
                MealStatus mealStatus, List<MealType> mealTypes, UUID childID) {
        this.mealPrice = mealPrice;
        this.mealFromDate = mealFromDate;
        this.mealToDate = mealToDate;
        this.mealStatus = mealStatus;
        this.mealTypes = mealTypes;
        this.childID = childID;
    }

    public void setMealPrice(BigDecimal mealPrice) {
        this.mealPrice = mealPrice;
    }

    public void setMealToDate(LocalDateTime mealToDate) {
        this.mealToDate = mealToDate;
    }

    public void setMealStatus(MealStatus mealStatus) {
        this.mealStatus = mealStatus;
    }

    public void setMealTypes(List<MealType> mealType) {
        this.mealTypes = mealTypes;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMealPrice() {
        return mealPrice;
    }

    public LocalDateTime getMealFromDate() {
        return mealFromDate;
    }

    public LocalDateTime getMealToDate() {
        return mealToDate;
    }

    public MealStatus getMealStatus() {
        return mealStatus;
    }

    public List<MealType> getMealTypes() {
        return mealTypes;
    }

    public UUID getChildID() {
        return childID;
    }
}
