package pl.edu.pja.prz.mail.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailProperties {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean smtpAuth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean smtpStarttls;
	@Value("${spring.mail.properties.mail.debug}")
	private boolean debug;

	public MailProperties() {
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isSmtpAuth() {
		return smtpAuth;
	}

	public boolean isSmtpStarttls() {
		return smtpStarttls;
	}

	public boolean isDebug() {
		return debug;
	}

	@Override
	public String toString() {
		return "MailProperties{" +
				"host='" + host + '\'' +
				", port='" + port + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", smtpAuth=" + smtpAuth +
				", smtpStarttls=" + smtpStarttls +
				", debug=" + debug +
				'}';
	}
}
