package pl.edu.pja.prz.meal.model.dto;

import pl.edu.pja.prz.meal.model.Meal;
import pl.edu.pja.prz.meal.model.enums.DietType;
import pl.edu.pja.prz.meal.model.enums.MealStatus;
import pl.edu.pja.prz.meal.model.enums.MealType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class MealCreateUpdateDTO {

	private BigDecimal mealPrice;
	private MealType mealType;
	private DietType dietType;
	private UUID childID;
	private LocalDate mealFromDate;
	private LocalDate mealToDate;

	public MealCreateUpdateDTO(BigDecimal mealPrice, MealType mealType, DietType dietType, UUID childID,
							   LocalDate mealFromDate, LocalDate mealToDate) {
		this.mealPrice = mealPrice;
		this.mealType = mealType;
		this.childID = childID;
		this.dietType = dietType;
		this.mealFromDate = mealFromDate;
		this.mealToDate = mealToDate;
	}

	public static Meal createMealFactory(MealCreateUpdateDTO dto) {
		return new Meal(dto.getMealPrice(),
				LocalDateTime.of(dto.getMealFromDate(), LocalTime.NOON),
				LocalDateTime.of(dto.getMealToDate(), LocalTime.MIDNIGHT),
				dto.getDietType(),
				MealStatus.ACTIVE,
				dto.getMealType(),
				dto.getChildID());
	}

	public BigDecimal getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(BigDecimal mealPrice) {
		this.mealPrice = mealPrice;
	}

	public LocalDate getMealFromDate() {
		return mealFromDate;
	}
	public LocalDate getMealToDate() {
		return mealToDate;
	}
	public MealType getMealType() {
		return mealType;
	}
	public DietType getDietType() {
		return dietType;
	}
	public UUID getChildID() {
		return childID;
	}


	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public void setDietType(DietType dietType) {
		this.dietType = dietType;
	}

	public void setChildID(UUID childID) {
		this.childID = childID;
	}

	public void setMealFromDate(LocalDate mealFromDate) {
		this.mealFromDate = mealFromDate;
	}

	public void setMealToDate(LocalDate mealToDate) {
		this.mealToDate = mealToDate;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MealCreateUpdateDTO that = (MealCreateUpdateDTO) o;
		return mealPrice.equals(that.mealPrice) &&
				mealType == that.mealType &&
				dietType == that.dietType &&
				childID.equals(that.childID) &&
				mealFromDate.equals(that.mealFromDate) &&
				mealToDate.equals(that.mealToDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mealPrice, mealType, dietType, childID, mealFromDate, mealToDate);
	}

	@Override
	public String toString() {
		return "MealCreateUpdateDTO{" +
				"mealPrice=" + mealPrice +
				", mealType=" + mealType +
				", dietType=" + dietType +
				", childID=" + childID +
				", mealFromDate=" + mealFromDate +
				", mealToDate=" + mealToDate +
				'}';
	}
}
