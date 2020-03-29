package pl.edu.pja.prz.meal.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.model.dto.NutritionalNotesDTO;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealNutritionalNotesRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NutritionalNotesServiceImplTest {

    @Mock
    private MealNutritionalNotesRepository mealNutritionalNotesRepository;
    @Mock
    private MealService mealService;

    private NutritionalNotesService nutritionalNotesService;
    List<NutritionalNotes> nutritionalNotes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        nutritionalNotesService = new NutritionalNotesServiceImpl(mealNutritionalNotesRepository, mealService);
    }



    @Test
    void ShouldGetAllNutritionalNotes_When_CorrectCall() {
        //given
        nutritionalNotes.add(new NutritionalNotes());
        nutritionalNotes.add(new NutritionalNotes());
        nutritionalNotes.add(new NutritionalNotes());

        //when
        when(mealNutritionalNotesRepository.findAll()).thenReturn(nutritionalNotes);

        //then
        Assert.assertEquals(nutritionalNotesService.getAllNutritionalNotes(), nutritionalNotes);
    }

    @Test
    void ShouldAddNutritionalNotes_When_CorrectCall() {
        //given
        Meal meal = new Meal(new BigDecimal("44.22"),
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                DietType.VEGAN,
                MealStatus.ACTIVE,
                MealType.BREAKFAST,
                UUID.randomUUID());

        NutritionalNotesDTO dto = new NutritionalNotesDTO("Dziś obiad był wyjątkowo dobry",
                99L,
                LocalDateTime.now());

        NutritionalNotes notes = new NutritionalNotes("Dziś obiad był wyjątkowo dobry",
                meal,
                LocalDateTime.now());


        //when
        when(mealService.getMealByID(99L)).thenReturn(meal);
        when(mealNutritionalNotesRepository.save(any(NutritionalNotes.class))).thenReturn(notes);

        //then
        NutritionalNotes nn = nutritionalNotesService.addNutritionalNotes(dto);
        Assert.assertThat(nn, instanceOf(NutritionalNotes.class));
        Assert.assertEquals("Dziś obiad był wyjątkowo dobry", nn.getNutritionalNotesValue());
    }

    @Test
    void getNutritionalNotesById() {
    }

    @Test
    void deleteNutritionalNotesById() {
    }
}
