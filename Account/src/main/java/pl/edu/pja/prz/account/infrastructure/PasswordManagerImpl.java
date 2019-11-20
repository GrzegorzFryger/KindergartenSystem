package pl.edu.pja.prz.account.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordManagerImpl implements PasswordManager {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public PasswordManagerImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodePassword) {
		return passwordEncoder.matches(rawPassword, encodePassword);
	}
}
