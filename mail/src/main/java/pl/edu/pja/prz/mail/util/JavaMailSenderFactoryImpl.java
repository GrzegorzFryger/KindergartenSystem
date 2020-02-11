package pl.edu.pja.prz.mail.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class JavaMailSenderFactoryImpl implements JavaMailSenderFactory {
    private final MailProperties mailProperties;

    @Autowired
    public JavaMailSenderFactoryImpl(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Override
    public JavaMailSender getSender() {
        JavaMailSenderImpl mailSender = initializeSender();
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        return mailSender;
    }

    @Override
    public JavaMailSender getSender(final String email, final String password) {
        JavaMailSenderImpl mailSender = initializeSender();
        mailSender.setUsername(email);
        mailSender.setPassword(password);
        return mailSender;
    }

	/**
	 * This method is used to initialize {@link JavaMailSenderImpl} with common properties
	 */
	private JavaMailSenderImpl initializeSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(Integer.parseInt(mailProperties.getPort()));
        mailSender.getSession().setDebug(mailProperties.isDebug());
        mailSender.setJavaMailProperties(getProperties());
        return mailSender;
    }

    private Properties getProperties() {
        var props = new Properties();
        props.put("spring.mail.protocol", mailProperties.getProtocol());
        props.put("mail.smtp.auth", mailProperties.isSmtpAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.isSmtpStartTls());
        return props;
    }

}
