package pl.edu.pja.prz.meal.service;


import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.math.BigDecimal;
import java.util.List;

public interface MealPriceService {

    MealPrice creatMealPrice(MealPrice mealPrice);
    MealPrice updateMealPrice(MealPrice mealPrice, long id);
    List<MealPrice> getAllPrices();
    void deleteMealPriceById(long id);
    BigDecimal getPriceByMealType(MealType mealType);
}
