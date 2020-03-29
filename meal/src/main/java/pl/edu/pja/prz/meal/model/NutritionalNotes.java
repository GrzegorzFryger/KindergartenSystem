package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NutritionalNotes extends BaseEntityLong {

    private String nutritionalNotesValue;
    @ManyToOne(fetch = FetchType.LAZY)
    private Meal meal;
    private LocalDateTime createdTime;

    public NutritionalNotes() {
    }

    public String getNutritionalNotesValue() {
        return nutritionalNotesValue;
    }

    public void setNutritionalNotesValue(String nutritionalNotesValue) {
        this.nutritionalNotesValue = nutritionalNotesValue;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public static NutritionalNotes create(NutritionalNotesDTO nutritionalNotesDTO) {
        return new NutritionalNotes();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NutritionalNotes that = (NutritionalNotes) o;
        return Objects.equals(nutritionalNotesValue, that.nutritionalNotesValue) &&
                Objects.equals(meal, that.meal) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nutritionalNotesValue, meal, createdTime);
    }

    @Override
    public String  toString() {
        return "NutritionalNotes{" +
                "nutritionalNotesValue='" + nutritionalNotesValue + '\'' +
                ", meal=" + meal +
                ", createdTime=" + createdTime +
                '}';
    }
}
