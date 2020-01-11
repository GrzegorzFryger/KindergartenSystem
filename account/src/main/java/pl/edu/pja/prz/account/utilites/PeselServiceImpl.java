package pl.edu.pja.prz.account.utilites;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PeselServiceImpl implements PeselService {
	private static final int PESELLENGTH = 12;
	private static final int[] WEIGHTFACTORS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
	private static final Integer[][] PESELNUMBERS = {{80, 1800}, {0, 1900}, {20, 2000}, {40, 2100}, {60, 2200}};
	private Predicate<Integer> isTwoDigit = x -> Integer.toString(Math.abs(x)).length() == 2;
	private IntUnaryOperator extractSingleDigit = x -> x % 10;
	private BiPredicate<int[], Integer> checkControlSumIsCorrect =
			(arrayPesel, sumMultiplyByWeight) -> (10 - sumMultiplyByWeight % 10) == arrayPesel[10];

	@Override
	public LocalDate extractDateOfBirth(String pesel) {
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

	@Override
	public Gender extractGender(String pesel) {
		if (checkSum(pesel)) {
			return (getGenderFromPesel(pesel) % 2 == 0) ? Gender.FEMALE : Gender.MALE;
		} else {
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

	private Integer getGenderFromPesel(String pesel) {
		return Integer.valueOf(pesel.substring(9, 10));
	}

	private Map<Integer, Integer> getMapOfPeselMontNumers() {
		return Stream.of(PESELNUMBERS).collect(Collectors.toMap(data -> data[0], data -> data[1]));
	}

	private DateOfBirth getDateOfBirthFromPesel(String pesel) {
		var dateOfBirth = new DateOfBirth();

		this.getMapOfPeselMontNumers().forEach((number, year) -> {
			if (1 <= (getMonthFromPesel(pesel) - number) && (getMonthFromPesel(pesel) - number) <= PESELLENGTH) {
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
			var tempSum = separatedPeselNumber[i] * WEIGHTFACTORS[i];

			if (isTwoDigit.test(tempSum)) {
				sumMultiplyByWeight += extractSingleDigit.applyAsInt(tempSum);
			} else sumMultiplyByWeight += tempSum;
		}
		return checkControlSumIsCorrect.test(separatedPeselNumber, sumMultiplyByWeight);
	}

	private class DateOfBirth {
		int day, month, year;
	}
}
