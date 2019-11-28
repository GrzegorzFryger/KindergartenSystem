package pl.edu.pja.prz.account.infrastructure;

import org.springframework.stereotype.Service;

@Service
public interface PasswordManager {
	String encode(CharSequence rawPassword);

	boolean matches(CharSequence rawPassword, String encodePassword);
}
