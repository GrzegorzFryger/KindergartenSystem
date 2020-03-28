package pl.edu.pja.prz.meal.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Meal extends BaseEntityLong {

    private BigDecimal mealPrice;
    private LocalDateTime mealFromDate;
    private LocalDateTime mealToDate;
    private MealStatus mealStatus;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    @Enumerated(EnumType.STRING)
    private DietType dietType;
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID childID;
    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<NutritionalNotes> nutritionalNotesList;


    public Meal() {
    }

    public Meal(BigDecimal mealPrice, LocalDateTime mealFromDate, LocalDateTime mealToDate, DietType dietType,
                MealStatus mealStatus, MealType mealType, UUID childID) {
        this.mealPrice = mealPrice;
        this.mealFromDate = mealFromDate;
        this.mealToDate = mealToDate;
        this.mealStatus = mealStatus;
        this.mealType = mealType;
        this.dietType = dietType;
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

    public DietType getDietType() {
        return dietType;
    }

    public UUID getChildID() {
        return childID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meal meal = (Meal) o;
        return mealPrice.equals(meal.mealPrice) &&
                mealFromDate.equals(meal.mealFromDate) &&
                mealToDate.equals(meal.mealToDate) &&
                mealStatus == meal.mealStatus &&
                mealType == meal.mealType &&
                dietType == meal.dietType &&
                childID.equals(meal.childID) &&
                nutritionalNotesList.equals(meal.nutritionalNotesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mealPrice, mealFromDate, mealToDate, mealStatus, mealType, dietType, childID, nutritionalNotesList);
    }


    @Override
    public String toString() {
        return "Meal{" +
                "mealPrice=" + mealPrice +
                ", mealFromDate=" + mealFromDate +
                ", mealToDate=" + mealToDate +
                ", mealStatus=" + mealStatus +
                ", mealType=" + mealType +
                ", dietType=" + dietType +
                ", childID=" + childID +
                ", nutritionalNotesList=" + nutritionalNotesList +
                '}';
    }
}
