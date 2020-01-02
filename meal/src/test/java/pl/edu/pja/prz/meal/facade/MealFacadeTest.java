package pl.edu.pja.prz.meal.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.service.MealServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealFacadeTest {

    @Mock
    private MealServiceImpl mealService;

    private MealFacade mealFacade;
    private MealCreateUpdateDTO mealCreateUpdateDTO;

    @BeforeEach
    void setUp() {
        mealFacade = new MealFacade(mealService);
    }

    @Test
    void createMeal() throws MealActivityStatusException {
        //when
        mealFacade.createMeal(mealCreateUpdateDTO);
        //then
        verify(mealService, times(1)).createMeal(mealCreateUpdateDTO);
    }
}
