package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;

@Entity
public class MealConfiguration extends BaseEntityLong {

    private String emailToSendMealOrder;

    public String getEmailToSendMealOrder() {
        return emailToSendMealOrder;
    }

}
