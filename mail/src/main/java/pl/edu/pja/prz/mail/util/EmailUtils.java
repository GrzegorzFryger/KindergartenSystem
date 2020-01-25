package pl.edu.pja.prz.mail.util;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtils {
    private EmailUtils() {

    }

    public static boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
