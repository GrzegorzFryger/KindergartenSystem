package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.facade.MealNutritionalNotesFacade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealNutritionalNotesControllerTest {

    @Mock
    private MealNutritionalNotesFacade mealNutritionalNotesFacade;
    private MealNutritionalNotesController mealNutritionalNotesController;

    @BeforeEach
    void setUp() {
        mealNutritionalNotesController = new MealNutritionalNotesController(mealNutritionalNotesFacade);
    }

    @Test
    void getAllNutritionalNotes() {
        //when
        mealNutritionalNotesController.getAllNutritionalNotes();

        //then
        verify(mealNutritionalNotesFacade, times(1)).getAllNutritionalNotes();
    }

    @Test
    void addNutritionalNotes() {
        //when
        mealNutritionalNotesController.addNutritionalNotes(any());

        //then
        verify(mealNutritionalNotesFacade, times(1)).addNutritionalNotes(any());
    }

    @Test
    void getNutritionalNotesById() {
        //when
        mealNutritionalNotesController.getNutritionalNotesById(any());

        //then
        verify(mealNutritionalNotesFacade, times(1)).getNutritionalNotesById(any());
    }

    @Test
    void deleteNutritionalNotesById() {
        //when
        mealNutritionalNotesController.deleteNutritionalNotesById(any(), any());

        //then
        verify(mealNutritionalNotesFacade, times(1)).deleteNutritionalNotesById(any(), any());
    }
}
