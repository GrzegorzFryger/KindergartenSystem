package pl.edu.pja.prz.mail.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.EmailUtils;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private static final String ENCODING = "UTF-8";
    private final JavaMailSenderFactory emailSenderFactory;

    @Autowired
    public MailServiceImpl(JavaMailSenderFactory emailSenderFactory) {
        this.emailSenderFactory = emailSenderFactory;
    }

    /**
     * Sending email from a default email account.<br>
     * Mail is taken from configuration method. For most cases this is recommended method.
     *
     * @param baseMail contains information about recipient
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException           in case of failure when sending a message
     * @throws MailException               as a general exception type
     */
    @Async
    @Override
    public void sendEmail(BaseMail baseMail) {
        //TODO handle above exceptions
        if (!validateInput(baseMail)) {
            logger.info("Sending email to: " + baseMail.getTo());
            emailSenderFactory.getSender().send(prepareMimeMessage(baseMail));
        }
    }

    /**
     * Sending email from a other account in the same domain.
     *
     * @param email    email, which you want to use to send email
     * @param password password to email, which you want to use to send email
     * @param baseMail contains information about recipient
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException           in case of failure when sending a message
     * @throws MailException               as a general exception type
     */
    @Async
    @Override
    public void sendEmail(String email, String password, BaseMail baseMail) {
        //TODO handle above exceptions
        if (validateInput(baseMail)) {
            if (EmailUtils.validateEmail(email)) {
                logger.info("Sending email to: " + baseMail.getTo());
                emailSenderFactory.getSender(email, password).send(prepareMimeMessage(baseMail));
            } else {
                logger.warn("Failed to send email. Following email address is incorrect: {}", email);
            }
        }
    }

    @Override
    public boolean validateInput(BaseMail baseMail) {
        if (!EmailUtils.validateEmail(baseMail.getTo())) {
            logger.warn("Input validation failure. Recipient email address is incorrect: {}", baseMail.getTo());
            return false;
        }
        if (StringUtils.isEmpty(baseMail.getSubject())) {
            logger.warn("Input validation failure. Subject is empty!");
            return false;
        }
        if (StringUtils.isEmpty(baseMail.getContent())) {
            logger.warn("Input validation failure. Content is empty!");
            return false;
        }
        return true;
    }

    private MimeMessagePreparator prepareMimeMessage(BaseMail dto) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, ENCODING);

            message.setTo(dto.getTo());
            message.setSubject(dto.getSubject());
            message.setText(dto.getContent(), true);

            if (dto.getAttachments().size() > 0) {
                dto.getAttachments().forEach((key, attachment) -> {
                    try {
                        message.addAttachment(key, attachment);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
    }

}




