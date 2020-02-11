package pl.edu.pja.prz.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMail;
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
     * Sending email from a default email account
     * @param baseMail -
     */

    @Async
    @Override
    public void sendEmail(BaseMail baseMail) {
        //todo handle with this exceptions
        //MailAuthenticationException - in case of authentication failure
        //MailSendException - in case of failure when sending a message
        //MailException
        emailSenderFactory.getSender().send(prepareMimeMessage(baseMail));
    }

    /**
     * Sending email from a other account in the same domain
     * @param email
     * @param password
     * @param baseMail
     */

    @Async
    @Override
    public void sendEmail(String email, String password, BaseMail baseMail) {
        //todo handle with this exceptions
        //MailAuthenticationException - in case of authentication failure
        //MailSendException - in case of failure when sending a message
        //MailException
        emailSenderFactory.getSender(email, password).send(prepareMimeMessage(baseMail));
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
                        }

                );
            }
        };
    }


}




