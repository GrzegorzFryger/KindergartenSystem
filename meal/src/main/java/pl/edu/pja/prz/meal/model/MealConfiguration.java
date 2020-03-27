package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class MealConfiguration extends BaseEntityLong {

    private String emailToSendMealOrder;
    public MealConfiguration() {
    }

    public String getEmailToSendMealOrder() {
        return emailToSendMealOrder;
    }

    public void setEmailToSendMealOrder(String emailToSendMealOrder) {
        this.emailToSendMealOrder = emailToSendMealOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MealConfiguration that = (MealConfiguration) o;
        return Objects.equals(emailToSendMealOrder, that.emailToSendMealOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), emailToSendMealOrder);
    }


    @Override
    public String toString() {
        return "MealConfiguration{" +
                "emailToSendMealOrder='" + emailToSendMealOrder + '\'' +
                '}';
    }
}
