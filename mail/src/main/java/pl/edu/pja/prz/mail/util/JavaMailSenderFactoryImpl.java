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
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(getProperties());
		mailSender.setHost(mailProperties.getHost());
		mailSender.setUsername(mailProperties.getUsername());
		mailSender.setPassword(mailProperties.getPassword());
		mailSender.setPort(Integer.parseInt(mailProperties.getPort()));
		mailSender.getSession().setDebug(mailProperties.isDebug());
		return mailSender;
	}

	@Override
	public JavaMailSender getSender(final String email, final String password) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(getProperties());
		mailSender.setHost(mailProperties.getHost());
		mailSender.setUsername(email);
		mailSender.setPassword(password);
		mailSender.setPort(Integer.parseInt(mailProperties.getPort()));
		mailSender.getSession().setDebug(mailProperties.isDebug());
		return mailSender;
	}

	private Properties getProperties() {
		var props = new Properties();
		props.put("spring.mail.protocol", mailProperties.getProtocol());
		props.put("mail.smtp.auth", mailProperties.isSmtpAuth());
		props.put("mail.smtp.starttls.enable", mailProperties.isSmtpStarttls());
		return props;
	}

}
