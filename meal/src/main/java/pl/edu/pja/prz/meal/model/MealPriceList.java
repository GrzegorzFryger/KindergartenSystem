package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class MealPriceList {

	@Id
	private long id;
	@Enumerated(EnumType.STRING)
	private MealType mealType;
	private double mealPrice;

	public MealPriceList() {
	}

	public long getId() {
		return id;
	}

	public MealType getMealType() {
		return mealType;
	}

	public double getMealPrice() {
		return mealPrice;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public void setMealPrice(double mealPrice) {
		this.mealPrice = mealPrice;
	}
}
