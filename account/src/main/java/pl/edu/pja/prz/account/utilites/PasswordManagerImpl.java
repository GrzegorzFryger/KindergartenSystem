package pl.edu.pja.prz.account.utilites;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordManagerImpl implements PasswordManager {
	private PasswordEncoder passwordEncoder;

	@Autowired
	public PasswordManagerImpl(PasswordEncoder passwordEncoder){
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

	@Override
	public String generateEncodeRandomPassword() {
		var password = generateLiteralCharacter(4).toUpperCase()
				.concat(generateLiteralCharacter(4).toLowerCase())
				.concat(generateDigitCharacter(2))
				.concat(generateSpecialCharacter(2));

		return this.encode(password);
	}

	private String generateDigitCharacter(int length) {
		return new RandomStringGenerator.Builder().
				withinRange(48, 57)
				.build()
				.generate(length);

	}
	private String generateLiteralCharacter(int length) {
		return new RandomStringGenerator.Builder().
				withinRange('A' , 'Z')
				.build()
				.generate(length);

	}
	private String generateSpecialCharacter(int length) {
		return new RandomStringGenerator.Builder().
				withinRange(34 , 46)
				.build()
				.generate(length);

	}
}
