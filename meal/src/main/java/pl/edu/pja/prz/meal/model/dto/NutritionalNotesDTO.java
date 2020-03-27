package pl.edu.pja.prz.meal.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class NutritionalNotesDTO {

    private String nutritionalNotesValue;
    private Long mealId;
    private LocalDateTime createdTime;

    public NutritionalNotesDTO() {
    }

    public String getNutritionalNotesValue() {
        return nutritionalNotesValue;
    }

    public void setNutritionalNotesValue(String nutritionalNotesValue) {
        this.nutritionalNotesValue = nutritionalNotesValue;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionalNotesDTO that = (NutritionalNotesDTO) o;
        return Objects.equals(nutritionalNotesValue, that.nutritionalNotesValue) &&
                Objects.equals(mealId, that.mealId) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nutritionalNotesValue, mealId, createdTime);
    }


    @Override
    public String toString() {
        return "NutritionalNotesDTO{" +
                "nutritionalNotesValue='" + nutritionalNotesValue + '\'' +
                ", mealId=" + mealId +
                ", createdTime=" + createdTime +
                '}';
    }
}
