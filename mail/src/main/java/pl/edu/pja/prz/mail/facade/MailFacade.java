package pl.edu.pja.prz.mail.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMailDTO;

import static pl.edu.pja.prz.mail.validator.EmailValidator.validateEmail;

@Service
public class MailFacade {
    private static final Logger logger = LoggerFactory.getLogger(MailFacade.class);

    public boolean sendEmail(String email, BaseMailDTO dto) {
        if (!validate(email)) {
            return false;
        }
        return false;
    }

    private boolean validate(String email) {
        if (validateEmail(email)) {
            return true;
        } else {
            logger.warn("Email validation failure! Incorrect email: " + email);
            logger.warn("Aborting sending emails. Please verify their correctness");
            return false;
        }
    }
}
