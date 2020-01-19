package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.meal.exception.MealPriceListAlreadyExistException;
import pl.edu.pja.prz.meal.exception.NotFoundException;
import pl.edu.pja.prz.meal.facade.MealPriceFacade;
import pl.edu.pja.prz.meal.model.MealPrice;

import java.util.List;

@RestController
@RequestMapping("api/meal/price")
public class MealPriceController {

    private final MealPriceFacade mealPriceFacade;

    @Autowired
    public MealPriceController(MealPriceFacade mealPriceFacade) {
        this.mealPriceFacade = mealPriceFacade;
    }

    @PostMapping
    public MealPrice creatMealPrice(@RequestBody  MealPrice mealPrice) throws MealPriceListAlreadyExistException {
        return mealPriceFacade.creatMealPrice(mealPrice);
    }

    @PutMapping()
    public MealPrice updateMealPrice(@RequestBody MealPrice mealPrice, @RequestParam("id") long id) throws NotFoundException {
        return mealPriceFacade.updateMealPrice(mealPrice, id);
    }

    @GetMapping
    public List<MealPrice> getAllPrices() {
       return mealPriceFacade.getAllPrices();
    }

    @DeleteMapping
    public void deleteMealPriceById(@RequestParam("id") long id) throws NotFoundException {
        mealPriceFacade.deleteMealPriceById(id);
    }


}