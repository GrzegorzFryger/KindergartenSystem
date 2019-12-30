package pl.edu.pja.prz.meal.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private MealType mealType;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childID;


    public Meal() {
    }

    public Meal(BigDecimal mealPrice, LocalDateTime mealFromDate, LocalDateTime mealToDate,
                MealStatus mealStatus, MealType mealType, UUID childID) {
        this.mealPrice = mealPrice;
        this.mealFromDate = mealFromDate;
        this.mealToDate = mealToDate;
        this.mealStatus = mealStatus;
        this.mealType = mealType;
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

    public void setMealTypes(MealType mealType) {
        this.mealType = mealType;
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

    public MealType getMealType() {
        return mealType;
    }

    public UUID getChildID() {
        return childID;
    }
}
