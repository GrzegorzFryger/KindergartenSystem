package pl.edu.pja.prz.core.controller.meal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MealControllerTest {

    @Mock
    private MealController mealController;
    private MealCreateUpdateDTO mealCreateUpdateDTO;

    @Test
    void createMeal() {
        //when
        mealController.createMeal(mealCreateUpdateDTO);

        //then
        verify(mealController, times(1)).createMeal(mealCreateUpdateDTO);
    }

    @Test
    void getAllMeals() {
        //when
        mealController.getAllMeals();

        //then
        verify(mealController, times(1)).getAllMeals();
    }

    @Test
    void getMealByID() {
        //when
        mealController.getMealByID(1L);

        //then
        verify(mealController, times(1)).getMealByID(1L);
    }

    @Test
    void updateMeal() {
        //when
        mealController.updateMeal(mealCreateUpdateDTO, 1L);

        //then
        verify(mealController, times(1)).updateMeal(mealCreateUpdateDTO, 1L);
    }
}
