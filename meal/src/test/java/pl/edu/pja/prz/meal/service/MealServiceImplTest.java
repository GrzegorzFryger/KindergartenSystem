package pl.edu.pja.prz.meal.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealPriceRepository mealPriceListRepository;
    private MealServiceImpl mealService ;


    @BeforeEach
    public void setup() {
        mealService = new MealServiceImpl(mealRepository, new MealPriceServiceImpl(mealPriceListRepository));
    }


    @Test
    public void createMeal() throws MealActivityStatusException {
        mealService = new MealServiceImpl(mealRepository, new MealPriceServiceImpl(mealPriceListRepository));
        MealCreateUpdateDTO mealCreateUpdateDTO = new MealCreateUpdateDTO(new BigDecimal(88),
                MealType.SOUP, UUID.randomUUID(), LocalDate.MIN, LocalDate.MAX);

        mealService.createMeal(mealCreateUpdateDTO);
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
    public void markMealAsInactiveOnDemand() {
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
