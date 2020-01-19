package pl.edu.pja.prz.meal.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.meal.exception.MealActivityStatusException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.dto.MealCreateUpdateDTO;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;
import pl.edu.pja.prz.meal.repository.MealPriceRepository;
import pl.edu.pja.prz.meal.repository.MealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealPriceRepository mealPriceListRepository;
    private MealServiceImpl mealService ;

    private MealCreateUpdateDTO mealCreateUpdateDTO;


    @BeforeEach
    public void setup() {
        MealPriceServiceImpl mealPriceService = new MealPriceServiceImpl(mealPriceListRepository);
        mealService = new MealServiceImpl(mealRepository, mealPriceService );

        mealCreateUpdateDTO =
                new MealCreateUpdateDTO(new BigDecimal(88), MealType.BREAKFAST, UUID.randomUUID(), LocalDate.MIN, LocalDate.MAX);
    }


    @Test
    public void shouldCreateMeal_When_InputArgumentIsCorrect() throws MealActivityStatusException {

        //given
        Meal meal = new Meal(new BigDecimal(99), LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID());

        //when
        when(mealRepository.findMealByChildIDAndMealStatusAndMealType(any(), any(), any())).thenReturn(Optional.empty());
        when(mealRepository.save(any())).thenReturn(meal);
        Meal createdMeal = mealService.createMeal(mealCreateUpdateDTO);

        //then
        Assert.assertNotNull(createdMeal);
        Assert.assertEquals(createdMeal.getMealStatus(), MealStatus.ACTIVE);
        Assert.assertNotNull(createdMeal.getMealPrice());
        Assert.assertNotNull(createdMeal.getChildID());
    }


    @Test
    public void shouldThrowMealActivityStatusException_When_TriedToCreateMealWhichIsAlreadyActive() {

        //given
        Meal meal = new Meal(new BigDecimal(99), LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID());

        //when
        when(mealRepository.findMealByChildIDAndMealStatusAndMealType(any(), any(), any())).thenReturn(Optional.of(meal));

        //then
        assertThrows(MealActivityStatusException.class, () -> {
            mealService.createMeal(mealCreateUpdateDTO);
        });
    }
    @Test
    public void shouldGetMealByID_When_MealExists() throws NotFoundException {
        //given
        when(mealRepository.findById(anyLong())).thenReturn(Optional.of(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO)));

        //when
        Meal meal = mealService.getMealByID(1L);

        //then
        Assert.assertNotNull(meal);

    }

    @Test
    public void shouldReturnNotFoundException_When_TriedToGetMealWithNotExist() {
        //given
        when(mealRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundException.class, () -> {
            mealService.getMealByID(1L);
        });


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
