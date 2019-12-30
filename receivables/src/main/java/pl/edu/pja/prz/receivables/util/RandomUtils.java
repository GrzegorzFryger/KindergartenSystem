package pl.edu.pja.prz.receivables.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUtils {
    private RandomUtils() {

    }

    public static String randomNumeric(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
