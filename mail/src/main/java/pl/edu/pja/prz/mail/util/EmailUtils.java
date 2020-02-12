package pl.edu.pja.prz.mail.util;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtils {
    private EmailUtils() {
    }

    /**
     * Checks if input is a valid e-mail address.
     *
     * @param email String which contains email address to be validated
     * @return true if the email address is valid.
     */
    public static boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
