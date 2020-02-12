package pl.edu.pja.prz.mail.service;

import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.EmailUtils;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import javax.mail.MessagingException;

import java.util.Locale;

import static org.apache.commons.lang.CharEncoding.UTF_8;
import static pl.edu.pja.prz.commons.constants.i18nConstants.POLISH_LOCALE;
import static pl.edu.pja.prz.mail.model.BaseMail.CONTENT;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private final JavaMailSenderFactory emailSenderFactory;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public MailServiceImpl(JavaMailSenderFactory emailSenderFactory, SpringTemplateEngine templateEngine) {
        this.emailSenderFactory = emailSenderFactory;
        this.templateEngine = templateEngine;
    }

    /**
     * Sending email from a default email account.<br>
     * Mail is taken from configuration method. For most cases this is recommended method.
     *
     * @param baseMail contains information about recipient
     * @throws MailPreparationException    in case of failure when preparing the message
     * @throws MailParseException          in case of failure when parsing the message
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException           in case of failure when sending the message
     */
    @Async
    @Override
    public void sendEmail(BaseMail baseMail) {
        if (validateInput(baseMail)) {
            try {
                logger.info("Sending email to: " + baseMail.getTo());
                emailSenderFactory.getSender().send(prepareMimeMessage(baseMail));
            } catch (MailSendException mse) {
                logger.error("Failed to send email to: " + baseMail.getTo(), mse);
            }
        }
    }

    /**
     * Sending email from a other account in the same domain.
     *
     * @param email    email, which you want to use to send email
     * @param password password to email, which you want to use to send email
     * @param baseMail contains information about recipient
     * @throws MailPreparationException    in case of failure when preparing the message
     * @throws MailParseException          in case of failure when parsing the message
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException           in case of failure when sending the message
     */
    @Async
    @Override
    public void sendEmail(String email, String password, BaseMail baseMail) {
        if (validateInput(baseMail)) {
            if (EmailUtils.validateEmail(email)) {
                try {
                    logger.info("Sending email to: " + baseMail.getTo());
                    emailSenderFactory.getSender(email, password).send(prepareMimeMessage(baseMail));
                } catch (MailSendException mse) {
                    logger.error("Failed to send email to: " + baseMail.getTo(), mse);
                }
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
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, UTF_8);

            Context context = new Context(POLISH_LOCALE);
            context.setVariable(CONTENT, dto.getContent());
            context.setVariables(dto.getVariables());
            String html = templateEngine.process(dto.getEmailTemplate().toString(), context);

            message.setTo(dto.getTo());
            message.setSubject(dto.getSubject());
            message.setText(html, true);

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




