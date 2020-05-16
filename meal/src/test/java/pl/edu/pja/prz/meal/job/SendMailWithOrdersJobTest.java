package pl.edu.pja.prz.meal.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobExecutionContext;
import pl.edu.pja.prz.calendar.service.DayOffWorkService;
import pl.edu.pja.prz.meal.service.MealServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendMailWithOrdersJobTest {

    @Mock
    private MealServiceImpl mealService;

    @Mock
    private DayOffWorkService dayOffWorkService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void Should_CreateSendMailWithOrdersJob() {
        //Given

        //When
        SendMailWithOrdersJob job = new SendMailWithOrdersJob(mealService, dayOffWorkService);

        //Then
        assertNotNull(job);
    }

    @Test
    public void Should_ExecuteJob_When_TodayIsNotDayOff() {
        //Given
        SendMailWithOrdersJob job = new SendMailWithOrdersJob(mealService, dayOffWorkService);

        //When
        when(dayOffWorkService.isTodayDayOff()).thenReturn(false);
        job.execute(jobExecutionContext);

        //Then
        verify(mealService, times(1)).markMealsAsInactiveIfNeeded();
        verify(mealService, times(1)).sendEmailWithOrder();
        verify(mealService, times(1)).chargeForMeal();
    }

    @Test
    public void Should_Not_ExecuteJob_When_TodayIsDayOff() {
        //Given
        SendMailWithOrdersJob job = new SendMailWithOrdersJob(mealService, dayOffWorkService);

        //When
        when(dayOffWorkService.isTodayDayOff()).thenReturn(true);
        job.execute(jobExecutionContext);

        //Then
        verify(mealService, never()).markMealsAsInactiveIfNeeded();
        verify(mealService, never()).sendEmailWithOrder();
        verify(mealService, never()).chargeForMeal();
    }
}