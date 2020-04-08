package pl.edu.pja.prz.meal.model.dto;

import java.util.Objects;

public class DictionaryObjectDTO {
    private String value;
    private String description;

    public DictionaryObjectDTO(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryObjectDTO that = (DictionaryObjectDTO) o;
        return value.equals(that.value) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, description);
    }

    @Override
    public String toString() {
        return "DictionaryObjectDTO{" +
                "value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
