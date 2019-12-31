package pl.edu.pja.prz.meal.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.service.MealPriceServiceImpl;

import java.util.List;

@Service
public class MealPriceFacade {

    private final MealPriceServiceImpl mealPriceService;

    @Autowired
    public MealPriceFacade(MealPriceServiceImpl mealPriceService) {
        this.mealPriceService = mealPriceService;
    }

    public MealPrice creatMealPrice(MealPrice mealPrice) throws MealPriceListAlreadyExistException {
        return mealPriceService.creatMealPrice(mealPrice);
    }

    public MealPrice updateMealPrice(MealPrice mealPrice, long id) throws NotFoundException {
        return mealPriceService.updateMealPrice(mealPrice, id);
    }

    public List<MealPrice> getAllPrices() {
        return mealPriceService.getAllPrices();
    }
    public void deleteMealPriceById(long id) throws NotFoundException {
        mealPriceService.deleteMealPriceById(id);
    }

}
