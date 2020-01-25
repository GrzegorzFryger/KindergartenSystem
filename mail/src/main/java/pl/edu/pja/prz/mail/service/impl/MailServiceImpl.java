package pl.edu.pja.prz.mail.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMailDTO;
import pl.edu.pja.prz.mail.service.MailService;

import static pl.edu.pja.prz.mail.util.EmailUtils.validateEmail;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public boolean sendEmail(String email, BaseMailDTO dto) {
        if (!validate(email)) {
            return false;
        }
        return send(email, dto);
    }

    private boolean send(String email, BaseMailDTO dto) {
        return true;
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
