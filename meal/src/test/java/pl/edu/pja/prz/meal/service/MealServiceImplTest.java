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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        mealService = new MealServiceImpl(mealRepository, new MealPriceServiceImpl(mealPriceListRepository));

        mealCreateUpdateDTO =
                new MealCreateUpdateDTO(new BigDecimal(1), MealType.BREAKFAST, UUID.randomUUID(), LocalDate.MIN, LocalDate.MAX);
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
    public void shouldDeleteMealByID_When_MealExists() throws NotFoundException {
        //given
        when(mealRepository.existsById(anyLong())).thenReturn(true);

        //when
        mealService.deleteMealByID(1L);

        verify(mealRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldReturnNotFoundException_When_TriedToDeleteMealWithNotExist() {

        //when
        assertThrows(NotFoundException.class, () -> {
            mealService.deleteMealByID(1L);
        });
    }

    @Test
    public void ShouldUpdateMeal_When_InputArgumentIsCorrect() throws NotFoundException, MealActivityStatusException {
        //given
        Meal meal = new Meal(new BigDecimal(88), LocalDateTime.MIN, LocalDateTime.now(),MealStatus.ACTIVE, MealType.SOUP, UUID.randomUUID());
        when(mealService.isMealPresentByID(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.empty());
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(mealRepository.save(meal)).thenReturn(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO));

        Meal updatedMeal = mealService.updateMeal(mealCreateUpdateDTO, 1L);

        Assert.assertEquals(updatedMeal.getMealPrice(), mealCreateUpdateDTO.getMealPrice());
        Assert.assertEquals(updatedMeal.getMealType(), mealCreateUpdateDTO.getMealType());
        Assert.assertEquals(updatedMeal.getMealToDate(), LocalDateTime.of(mealCreateUpdateDTO.getMealToDate(), LocalTime.MIDNIGHT));
    }

    @Test
    public void ShouldReturnNotFoundException_When_TriedToUpdateMealWithNotExist() {

        assertThrows(NotFoundException.class, () -> {
            mealService.updateMeal(mealCreateUpdateDTO, 1L);
        });
    }

    @Test
    public void ShouldReturnMealActivityStatusException_When_TriedToUpdateMealWithIsNotActive() {

        //given
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.INACTIVE, MealType.BREAKFAST, UUID.randomUUID() );
        when(mealRepository.existsById(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(1L, MealStatus.INACTIVE)).thenReturn(Optional.of(MealCreateUpdateDTO.createMealFactory(mealCreateUpdateDTO)));

        //then
        assertThrows(MealActivityStatusException.class, () -> {
            mealService.updateMeal(mealCreateUpdateDTO, 1L);
        });
    }

    @Test
    public void ShouldMarkMealAsInactiveOnDemand_When_InputArgumentIsCorrect() throws NotFoundException, MealActivityStatusException {
        //given
        when(mealRepository.existsById(1L)).thenReturn(true);
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.empty());
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() );

        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        //when
        mealService.markMealAsInactiveOnDemand(1L);

        //then
        assertEquals(meal.getMealStatus(), MealStatus.INACTIVE);
    }

    @Test
    public void ShouldReturnNotFoundException_When_TriedToMarkMealAsInactiveOnDemandWithNotExist() {
        assertThrows(NotFoundException.class, () -> {
            mealService.markMealAsInactiveOnDemand(111L);
        });
    }

    @Test
    public void ShouldReturnMealActivityStatusException_When_TriedToMarkMealAsInactiveOnDemandWithIsNotActive() {
        //given
        when(mealRepository.existsById(111L)).thenReturn(true);
        Meal meal = new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() );
        when(mealRepository.findMealByIdAndMealStatus(any(), any())).thenReturn(Optional.of(meal));


        //then
        assertThrows(MealActivityStatusException.class, () -> {
            mealService.markMealAsInactiveOnDemand(111L);
        });
    }

    @Test
    public void ShouldGetAllMeals_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.INACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        when(mealRepository.findAll()).thenReturn(meals);

        //when
        List<Meal> returnedMeals = mealService.getAllMeals();

        //then
        assertEquals(meals, returnedMeals);
    }

    @Test
    public void ShouldGetAllActiveMeals_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MAX, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        when(mealRepository.findAllByMealStatus(MealStatus.ACTIVE)).thenReturn(meals);

        //when
        List<Meal> returnedMeals = mealService.getAllActiveMeals();

        //then
        assertEquals(meals, returnedMeals);
    }

    @Test
    public void ShouldMarkMealsAsInactiveIfNeeded_When_InputArgumentIsCorrect() {
        //given
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        meals.add(new Meal(BigDecimal.ONE, LocalDateTime.MIN, LocalDateTime.MIN, MealStatus.ACTIVE, MealType.BREAKFAST, UUID.randomUUID() ));
        when(mealRepository.findAllByMealStatus(MealStatus.ACTIVE)).thenReturn(meals);

        List<Meal> afterInactiveMark = mealService.markMealsAsInactiveIfNeeded();

        assertEquals(afterInactiveMark.get(1).getMealStatus(), MealStatus.INACTIVE);

    }

    @Test
    public void ShouldIsMealPresentByID_When_InputArgumentIsCorrect() {
        when(mealRepository.existsById(1L)).thenReturn(true);
        assertTrue(mealService.isMealPresentByID(1L));
    }
}
