package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.facade.MealDictionaryFacade;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DictionaryControllerTest {

    @Mock
    private MealDictionaryFacade mealDictionaryFacade;
    private DictionaryController dictionaryController;

    @BeforeEach
    public void setUp() {
        dictionaryController = new DictionaryController(mealDictionaryFacade);
    }


    @Test
    void getAllMealTypes() {
        //when
        dictionaryController.getAllMealTypes();

        //then
        verify(mealDictionaryFacade, times(1)).getAllMealTypes();
    }

    @Test
    void getAllDietType() {
        //when
        dictionaryController.getAllDietType();

        //then
        verify(mealDictionaryFacade, times(1)).getAllDietType();
    }
}
