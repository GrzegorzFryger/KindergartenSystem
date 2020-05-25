package pl.edu.pja.prz.core.controller.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.meal.facade.MealOrderFacade;
import pl.edu.pja.prz.meal.model.MealOrder;

import java.time.LocalDate;
import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_MEAL_ORDER;

@RestController
@RequestMapping(API_MEAL_ORDER)
public class MealOrderController {

    private final MealOrderFacade mealOrderFacade;

    @Autowired
   public MealOrderController(MealOrderFacade mealOrderFacade) {
        this.mealOrderFacade = mealOrderFacade;
    }

    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<MealOrder> getAllMealOrder() {
        return mealOrderFacade.getAllMealOrder();
    }

    @GetMapping("/{date}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<MealOrder> getAllMealOrderByDate(@PathVariable("date") String orderDate) {
        return mealOrderFacade.getAllMealOrderByDate(LocalDate.parse(orderDate));
    }


}
