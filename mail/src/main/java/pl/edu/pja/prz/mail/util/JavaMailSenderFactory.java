package pl.edu.pja.prz.mail.util;

import org.springframework.mail.javamail.JavaMailSender;

public interface JavaMailSenderFactory {
	JavaMailSender getSender();

	JavaMailSender getSender(String email, String password);
}
