package pl.edu.pja.prz.meal.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.pja.prz.meal.exception.MeaActivityStatusException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceImplTest {

    @Mock
    MealRepository mealRepository;
    @Mock
    MealServiceImpl mealServiceMock;

    @Test(expected = MeaActivityStatusException.class)
    public void tryToCreateMealWhenAlreadyOneActive_MeaActivityStatusExceptionExpected() throws MeaActivityStatusException {
        //given
        MealCreateUpdateDTO dto = new MealCreateUpdateDTO(10, MealType.BREAKFAST,1, LocalDate.MIN, LocalDate.MAX);
        Mockito.when(mealRepository.findMealByChildIDAndMealStatus(1, MealStatus.ACTIVE)).thenReturn(Optional.of(new Meal()));
        Mockito.when(mealServiceMock.createMeal(dto)).thenCallRealMethod();
        //when
        mealServiceMock.createMeal(dto);
        //then
    }

    @Test
    public void getMealByID() {
    }

    @Test
    public void deleteMealByID() {
    }

    @Test
    public void updateMeal() {
    }

    @Test
    public void getAllMeals() {
    }

    @Test
    public void getAllActiveMeals() {
    }

    @Test
    public void markMealsAsInactiveIfNeeded() {
    }

    @Test
    public void isMealPresentByID() {
    }
}
