package pl.edu.pja.prz.meal.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MealCreateUpdateDTOTest {
    private MealCreateUpdateDTO dto;
    private MealCreateUpdateDTO dto2;

    @BeforeEach
    public void setUp() {
        UUID childId = UUID.randomUUID();
        dto = new MealCreateUpdateDTO(new BigDecimal(1), MealType.BREAKFAST, DietType.VEGAN, childId,
                        LocalDate.MIN, LocalDate.MAX, new ArrayList<>());
        dto2 = new MealCreateUpdateDTO(new BigDecimal(1), MealType.BREAKFAST, DietType.VEGAN, childId,
                        LocalDate.MIN, LocalDate.MAX, new ArrayList<>());

    }

    @Test
    public void sShould_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int code1 = dto.hashCode();
        int code2 = dto2.hashCode();

        //Then
        assertEquals(code1, code2);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_ObjectsAreDifferent() {
        //Given

        //When
        dto.setChildID(UUID.randomUUID());
        dto.setDietType(DietType.BASIC);
        dto.setMealPrice(new BigDecimal(2));
        dto.setMealType(MealType.DINER);

        int code1 = dto.hashCode();
        int code2 = dto2.hashCode();

        //Then
        assertNotEquals(code1, code2);
    }

    @Test

    public void Should_ReturnTrue_When_ObjectsAreEqual() {
        //Given

        //When
        boolean firstToSecond = dto.equals(dto2);
        boolean secondToFirst = dto2.equals(dto);

        //Then
        assertTrue(firstToSecond);
        assertTrue(secondToFirst);
    }
}