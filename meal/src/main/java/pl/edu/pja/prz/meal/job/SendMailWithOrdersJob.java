package pl.edu.pja.prz.meal.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import pl.edu.pja.prz.meal.service.MealServiceImpl;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;

@QuartzJob(name = "meals", description = "send mail with meals order")
public class SendMailWithOrdersJob extends MealJob{

    public SendMailWithOrdersJob(MealServiceImpl mealService) {
        super(mealService);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        mealService.markMealsAsInactiveIfNeeded();
        mealService.sendEmailWithOrder();
        mealService.chargeForMeal();
    }
}
