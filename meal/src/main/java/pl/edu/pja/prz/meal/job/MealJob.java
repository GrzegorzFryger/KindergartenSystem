package pl.edu.pja.prz.meal.job;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.calendar.service.DayOffWorkService;
import pl.edu.pja.prz.meal.service.MealServiceImpl;

@Component
public abstract class MealJob implements Job {

    protected MealServiceImpl mealService;
    protected DayOffWorkService dayOffWorkService;

    @Autowired
    public MealJob(MealServiceImpl mealService, DayOffWorkService dayOffWorkService) {
        this.dayOffWorkService = dayOffWorkService;
        this.mealService = mealService;
    }
}
