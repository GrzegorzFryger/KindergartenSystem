package pl.edu.pja.prz.meal.model;

import org.hibernate.annotations.Type;
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
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NutritionalNotes> nutritionalNotesList;


    public Meal() {
    }



    public Meal(BigDecimal mealPrice, LocalDateTime mealFromDate, LocalDateTime mealToDate, DietType dietType,
                MealStatus mealStatus, MealType mealType, UUID childID, List<NutritionalNotes> nutritionalNotesList) {
        this.mealPrice = mealPrice;
        this.mealFromDate = mealFromDate;
        this.mealToDate = mealToDate;
        this.mealStatus = mealStatus;
        this.mealType = mealType;
        this.dietType = dietType;
        this.childID = childID;
        this.nutritionalNotesList = nutritionalNotesList;
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

    public void setMealFromDate(LocalDateTime mealFromDate) {
        this.mealFromDate = mealFromDate;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    public void setChildID(UUID childID) {
        this.childID = childID;
    }

    public List<NutritionalNotes> getNutritionalNotesList() {
        return nutritionalNotesList;
    }

    public void setNutritionalNotesList(List<NutritionalNotes> nutritionalNotesList) {
        this.nutritionalNotesList = nutritionalNotesList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meal meal = (Meal) o;
        return Objects.equals(mealPrice, meal.mealPrice) &&
                Objects.equals(mealFromDate, meal.mealFromDate) &&
                Objects.equals(mealToDate, meal.mealToDate) &&
                mealStatus == meal.mealStatus &&
                mealType == meal.mealType &&
                dietType == meal.dietType &&
                Objects.equals(childID, meal.childID) &&
                Objects.equals(nutritionalNotesList, meal.nutritionalNotesList);
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
