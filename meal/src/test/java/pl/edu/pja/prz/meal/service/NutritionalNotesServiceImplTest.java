package pl.edu.pja.prz.meal.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.NutritionalNotes;
import pl.edu.pja.prz.meal.repository.MealNutritionalNotesRepository;

import java.util.ArrayList;
import java.util.List;

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
    void addNutritionalNotes() {
        //given
        Meal meal = new Meal(b)
    }

    @Test
    void getNutritionalNotesById() {
    }

    @Test
    void deleteNutritionalNotesById() {
    }
}
