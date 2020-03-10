package pl.edu.pja.prz.commons.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
    private BigDecimalUtils() {

    }

    public static boolean isPositive(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isNegative(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    public static BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}
