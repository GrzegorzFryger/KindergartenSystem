package pl.edu.pja.prz.account.infrastructure;

public interface PasswordManager {
	String encode(CharSequence rawPassword);
	boolean matches(CharSequence rawPassword, String encodePassword);
}
