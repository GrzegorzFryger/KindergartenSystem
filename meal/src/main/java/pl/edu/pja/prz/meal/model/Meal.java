package pl.edu.pja.prz.meal.model;

import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double mealPrice;
	private LocalDateTime mealFromDate;
	private LocalDateTime mealToDate;
	private MealStatus mealStatus;
	@Enumerated(EnumType.STRING)
	private MealType mealType;
	private long childID;


	public Meal() {
	}

	public Meal(double mealPrice, LocalDateTime mealFromDate, LocalDateTime mealToDate, MealStatus mealStatus, MealType mealType, long childID) {
		this.mealPrice = mealPrice;
		this.mealFromDate = mealFromDate;
		this.mealToDate = mealToDate;
		this.mealStatus = mealStatus;
		this.mealType = mealType;
		this.childID = childID;
	}

	public void setMealPrice(double mealPrice) {
		this.mealPrice = mealPrice;
	}

	public void setMealToDate(LocalDateTime mealToDate) {
		this.mealToDate = mealToDate;
	}

	public void setMealStatus(MealStatus mealStatus) {
		this.mealStatus = mealStatus;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public Long getId() {
		return id;
	}

	public double getMealPrice() {
		return mealPrice;
	}

	public LocalDateTime getMealFromDate() {
		return mealFromDate;
	}

	public LocalDateTime getMealToDate() {
		return mealToDate;
	}

	public MealStatus getMealStatus() {
		return mealStatus;
	}

	public MealType getMealType() {
		return mealType;
	}

	public long getChildID() {
		return childID;
	}
}
