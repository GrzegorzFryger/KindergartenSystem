package pl.edu.pja.prz.meal.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.service.MealDictionaryService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealDictionaryFacadeTest {

    @Mock
    private MealDictionaryService mealDictionaryService;

    private MealDictionaryFacade mealDictionaryFacade;

    @BeforeEach
    public void setUp() {
        mealDictionaryFacade = new MealDictionaryFacade(mealDictionaryService);
    }

    @Test
    void Should_GetAllMealTypes() {

        //when
        mealDictionaryFacade.getAllMealTypes();

        //then
        verify(mealDictionaryService, times(1)).getAllMealTypes();
    }
}
