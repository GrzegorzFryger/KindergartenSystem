package pl.edu.pja.prz.meal.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import pl.edu.pja.prz.calendar.service.DayOffWorkService;
import pl.edu.pja.prz.meal.service.MealServiceImpl;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;

@QuartzJob(name = "meals", description = "send mail with meals order")
public class SendMailWithOrdersJob extends MealJob{

    private DayOffWorkService dayOffWorkService;

    public SendMailWithOrdersJob(MealServiceImpl mealService, DayOffWorkService dayOffWorkService) {
        super(mealService, dayOffWorkService);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(!dayOffWorkService.isTodayDayOff()) {
            mealService.markMealsAsInactiveIfNeeded();
            mealService.sendEmailWithOrder();
            mealService.chargeForMeal();
        }
    }
}
