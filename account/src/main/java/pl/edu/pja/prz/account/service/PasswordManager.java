package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordManager {
	String encode(CharSequence rawPassword);

	boolean matches(CharSequence rawPassword, String encodePassword);
}
