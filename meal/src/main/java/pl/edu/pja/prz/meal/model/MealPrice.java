package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class MealPrice {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private BigDecimal mealPrice;

    public MealPrice() {
    }

    public MealPrice(MealType mealType, BigDecimal mealPrice) {
        this.mealType = mealType;
        this.mealPrice = mealPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MealPrice mealPrice1 = (MealPrice) o;
        return mealType == mealPrice1.mealType &&
                Objects.equals(mealPrice, mealPrice1.mealPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mealType, mealPrice);
    }

    @Override
    public String toString() {
        return "MealPrice{" +
                "mealType=" + mealType +
                ", mealPrice=" + mealPrice +
                '}';
    }
}
