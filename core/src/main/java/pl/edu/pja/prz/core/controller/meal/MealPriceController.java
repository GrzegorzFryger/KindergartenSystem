package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.meal.facade.MealPriceFacade;
import pl.edu.pja.prz.meal.model.MealPrice;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_MEALS_PRICE;

@RestController
@RequestMapping(API_MEALS_PRICE)
@CrossOrigin("*")
public class MealPriceController {

    private final MealPriceFacade mealPriceFacade;

    @Autowired
    public MealPriceController(MealPriceFacade mealPriceFacade) {
        this.mealPriceFacade = mealPriceFacade;
    }

    @PostMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public MealPrice creatMealPrice(@RequestBody MealPrice mealPrice) {
        return mealPriceFacade.creatMealPrice(mealPrice);
    }

    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public MealPrice updateMealPrice(@RequestBody MealPrice mealPrice, @RequestParam("id") long id) {
        return mealPriceFacade.updateMealPrice(mealPrice, id);
    }

    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<MealPrice> getAllPrices() {
        return mealPriceFacade.getAllPrices();
    }

    @DeleteMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void deleteMealPriceById(@RequestParam("id") long id) {
        mealPriceFacade.deleteMealPriceById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public MealPrice getMealPriceById(@PathVariable("id") long id) {
        return mealPriceFacade.getById(id);
    }

    @GetMapping("/notSet")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<MealType> getAllNotSetMealPrice() {
        return mealPriceFacade.getAllNotSetMealPrice();
    }

}
