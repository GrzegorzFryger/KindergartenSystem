package pl.edu.pja.prz.commons.util;

import java.time.LocalDate;

public class DateUtils {

    private DateUtils() {

    }

    public static LocalDate getDateOneMonthBehind(LocalDate toDate) {
        if (toDate == null) {
            return null;
        }
        return toDate.minusMonths(1L);
    }
}
