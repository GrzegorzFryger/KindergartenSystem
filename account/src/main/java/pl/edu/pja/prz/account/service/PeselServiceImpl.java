package pl.edu.pja.prz.account.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeselServiceImpl implements PeselService {

	private final int[] weightsFactors = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
	private final Map<Integer, Integer> mapOfNumbersForMonth = Stream.of(new Integer[][]{
			{80, 1800},
			{0, 1900},
			{20, 2000},
			{40, 2100},
			{60, 2200},
	}).collect(Collectors.toMap(data -> data[0], data -> data[1]));

	private Function<Integer, Boolean> isTwoDigit = x -> Integer.toString(Math.abs(x)).length() == 2;
	private Function<Integer, Integer> extractSingleDigit = x -> x % 10;
	private BiFunction<int[], Integer, Boolean> checkControlSumIsCorrect =
			(arrayPesel, sumMultiplyByWeight) -> (10 - sumMultiplyByWeight % 10) == arrayPesel[10];

	@Override
	public LocalDate generateDateFromPesel(String pesel) {
		try {
			DateOfBirth dateOfBirth = new PeselServiceImpl.DateOfBirth();

			if (checkSum(pesel)) {
				dateOfBirth = getDateOfBirthFromPesel(pesel);
			}

			return LocalDate.of(dateOfBirth.year, dateOfBirth.month, dateOfBirth.day);
		} catch (DateTimeException e) {
			//todo write custom exceptions
			throw new IllegalArgumentException("Wrong pesel number");
		}
	}

	private int[] toArrayOfNumbers(String pesel) {
		return pesel.chars().map(Character::getNumericValue).toArray();
	}

	private Integer getYearFromPesel(String pesel) {
		return Integer.valueOf(pesel.substring(0, 2));
	}

	private Integer getMonthFromPesel(String pesel) {
		return Integer.valueOf(pesel.substring(2, 4));
	}

	private Integer getDayFromPesel(String pesel) {
		return Integer.valueOf(pesel.substring(4, 6));
	}

	private DateOfBirth getDateOfBirthFromPesel(String pesel) {
		var dateOfBirth = new DateOfBirth();

		mapOfNumbersForMonth.forEach((number, year) -> {

			if (0 < (getMonthFromPesel(pesel) - number) && (getMonthFromPesel(pesel) - number) < 13) {

				dateOfBirth.day = getDayFromPesel(pesel);
				dateOfBirth.month = getMonthFromPesel(pesel) - number;
				dateOfBirth.year = year + getYearFromPesel(pesel);
			}
		});

		return dateOfBirth;
	}

	private boolean checkSum(String pesel) {
		var separatedPeselNumber = toArrayOfNumbers(pesel);

		var sumMultiplyByWeight = 0;

		for (var i = 0; i < 10; i++) {
			var tempSum = separatedPeselNumber[i] * weightsFactors[i];

			if (isTwoDigit.apply(tempSum)) {
				sumMultiplyByWeight += extractSingleDigit.apply(tempSum);
			} else sumMultiplyByWeight += tempSum;
		}
		return checkControlSumIsCorrect.apply(separatedPeselNumber, sumMultiplyByWeight);
	}

	private class DateOfBirth {
		int day, month, year;
	}

}
