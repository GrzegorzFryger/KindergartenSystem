package pl.edu.pja.prz.meal.model.dto;

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
}
