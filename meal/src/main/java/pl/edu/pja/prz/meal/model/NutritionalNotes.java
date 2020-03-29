package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NutritionalNotes extends BaseEntityLong {

    private String nutritionalNotesValue;
    private LocalDateTime createdTime;

    public NutritionalNotes() {
    }

    public NutritionalNotes(String nutritionalNotesValue,  LocalDateTime createdTime) {
        this.nutritionalNotesValue = nutritionalNotesValue;
        this.createdTime = createdTime;
    }

    public String getNutritionalNotesValue() {
        return nutritionalNotesValue;
    }

    public void setNutritionalNotesValue(String nutritionalNotesValue) {
        this.nutritionalNotesValue = nutritionalNotesValue;
    }


    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public static NutritionalNotes create(NutritionalNotesDTO dto) {
        return new NutritionalNotes(
                dto.getNutritionalNotesValue(),
                LocalDateTime.now()
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NutritionalNotes that = (NutritionalNotes) o;
        return Objects.equals(nutritionalNotesValue, that.nutritionalNotesValue) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nutritionalNotesValue, createdTime);
    }

    @Override
    public String  toString() {
        return "NutritionalNotes{" +
                "nutritionalNotesValue='" + nutritionalNotesValue + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
