package org.example.prospero.data.pesel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.prospero.data.domain.enums.Gender;

import java.text.ParseException;
import java.time.LocalDate;

public class PeselParser {

	public static Pesel parse(String peselString) throws ParseException {
		if (!isValidPesel(peselString)) {
			throw new ParseException("", 0);
		}

		var birthday = getBirthdayFromPesel(peselString);
		var gender = getGenderFromPesel(peselString);

		return new Pesel(peselString, birthday, gender);
	}

	public static boolean isValidPesel(String peselString) {
		if (peselString == null || peselString.length() != 11 || !peselString.matches("\\d+")) {
			return false;
		}

		var checksum = Integer.parseInt(peselString.substring(10));
		var peselChars = peselString.toCharArray();
		var sum = 0;

		for (int i = 0; i < peselChars.length - 1; i++) {
			var digit = Character.getNumericValue(peselChars[i]);
			sum += digit * getMultiplier(i);
		}

		var modulo = sum % 10;

		if (modulo == 0 && checksum == 0) {
			return true;
		}
		return 10 - modulo == checksum;
	}

	private static LocalDate getBirthdayFromPesel(String peselString) {
		var day = Integer.parseInt(peselString.substring(4, 6));
		var peselMonth = Integer.parseInt(peselString.substring(2, 4));
		var actualMonth = getMonthFromPeselMonth(peselMonth);
		var year = getBaseYearFromPeselMonth(peselMonth) + Integer.parseInt(peselString.substring(0, 2));

		return LocalDate.of(year, actualMonth, day);
	}

	private static Gender getGenderFromPesel(String peselString) {
		var genderDigit = Integer.parseInt(peselString.substring(9, 10));
		return genderDigit >> 1 == 0 ? Gender.FEMALE : Gender.MALE;
	}

	private static int getBaseYearFromPeselMonth(int peselMonth) {
		var monthAddition = peselMonth / 10;

		if (monthAddition >= 8) {
			return 1800;
		}
		if (monthAddition >= 6) {
			return 2200;
		}
		if (monthAddition >= 4) {
			return 2100;
		}
		if (monthAddition >= 2) {
			return 2000;
		}
		return 1900;
	}

	private static int getMonthFromPeselMonth(int peselMonth) {
		if (peselMonth <= 12) {
			return peselMonth;
		}

		var monthAddition = peselMonth / 10;

		if (monthAddition >> 1 == 1) {
			monthAddition--;
		}

		return peselMonth - (monthAddition * 10);
	}

	private static int getMultiplier(int index) {
		return switch (index % 4) {
			case 0 -> 1;
			case 1 -> 3;
			case 2 -> 7;
			case 3 -> 9;
			default -> throw new IllegalStateException("Unexpected value: " + index % 4);
		};
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Pesel {

		private final String pesel;
		private final LocalDate birthday;
		private final Gender gender;
	}
}
